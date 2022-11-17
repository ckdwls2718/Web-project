package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class BoardWriteFormAction extends AbstractAction{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		setRedirect(false);
		setViewPage("/board/boardWrite.jsp");
	}
}
