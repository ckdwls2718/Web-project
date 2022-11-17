package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardEditFormAction extends AbstractAction {

	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		if(user==null) {
			req.setAttribute("msg", "로그인 후 이용하세요");
			req.setAttribute("loc", "login.do");
			
			setViewPage("message.jsp");
			setRedirect(false);
			return;
		}
		
		String numStr = req.getParameter("num");
		
		if(numStr == null || numStr.isBlank()) {
			setRedirect(true);
			setViewPage("boardList.do");
			return;
		}
		
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		
		BoardVO vo = dao.viewBoard(Integer.parseInt(numStr));
		
		if(!vo.getUserid().equals(user.getUserid())) {
			
			req.setAttribute("msg", "수정권한이 없습니다");
			req.setAttribute("loc", "javascript:history.back()");
			
			setRedirect(false);
			setViewPage("message.jsp");
			
			return;
		}
		
		req.setAttribute("board", vo);
		
		setRedirect(false);
		setViewPage("board/boardEdit.jsp");

	}

}
