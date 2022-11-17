package board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardDeleteAction extends AbstractAction {

	@Override
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
		/*#글 삭제 처리
		/boardDel.do=board.controller.BoardDeleteAction

		BoardDeleteAction클래스 작성
		[1] 삭제할 글 번호 받기
		[2] 유효성 체크 => boardList.do  redirect이동
		[3] dao의 deleteBoard(num)
		[4] 실행결과 메시지 및 이동 경로 지정
		   => req에 저장. msg, loc
		[5] 뷰페이지 지정/이동방식 지정
			=> message.jsp
		*/

		if(numStr == null || numStr.isBlank()) {
			setViewPage("boardList.do");
			setRedirect(true);
			return;
		}
			
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		
		BoardVO vo = dao.viewBoard(Integer.parseInt(numStr));
		
		if(!vo.getUserid().equals(user.getUserid())) {
			
			req.setAttribute("msg", "삭제 권한이 없습니다");
			req.setAttribute("loc", "javascript:history.back()");
			
			setRedirect(false);
			setViewPage("message.jsp");
			
			return;
		}
		
		if( vo.getFilename()!= null) { //첨부파일이 있다면 서버에서 지운다
			String updir = req.getServletContext().getRealPath("/upload");
			File delFile = new File(updir,vo.getFilename());
			if(delFile!=null) {
				delFile.delete();
			}
		}
		
		int n = dao.deleteBoard(Integer.parseInt(numStr));
		
		String msg = (n>0) ? "글 삭제 성공" : "글 삭제 실패";
		String loc = "boardList.do";
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		
		setViewPage("message.jsp");
		setRedirect(false);
		
	}

}
