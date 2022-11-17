package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String num = req.getParameter("num");
		
		if(num == null || num.isBlank()) {
			setRedirect(true);
			setViewPage("boardList.do");
			return;
		}
		
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		
		BoardVO vo = dao.viewBoard(Integer.parseInt(num));
		
		req.setAttribute("board", vo);
		
		setRedirect(false);
		setViewPage("board/boardView.jsp");
	}

}
