package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String cpStr = req.getParameter("cpage");
		if(cpStr==null || cpStr.isBlank()) {
			cpStr = "1"; //1페이지를 기본값으로 설정
		}
		int cpage = Integer.parseInt(cpStr);
		if(cpage<1) {
			cpage = 1;
		}
		
		String findType = req.getParameter("findType");
		String findKeyword = req.getParameter("findKeyword");
		
		if(findType==null) {
			findType="";
		}
		if(findKeyword==null) {
			findKeyword="";
		}
		
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		// 총 게시글 수 구하기
		int totalCount = dao.getTotalCount(findType, findKeyword);
		
		// 한페이지당 보여줄 목록 개수 정하기
		int pageSize = 5;
		
		// 페이지 수 구하기
		//int pageCount = (totalCount % pageSize == 0) ? totalCount/pageSize : (totalCount/pageSize)+1;
		int pageCount = (totalCount-1)/pageSize + 1;
		
		if(pageCount<=0) {
			pageCount=1;
		}
		
		if(cpage > pageCount) {
			cpage = pageCount; // 존재하는 페이지보다 큰값이 들어오면 마지막 페이지를 보여준다
		}
		
		int end = cpage * pageSize;
		int start = end -(pageSize-1);
		
		List<BoardVO> boardArr = dao.listBoard(start, end, findType, findKeyword);
		String qStr = "&findType="+findType+"&findKeyword="+findKeyword;
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("boardArr", boardArr);
		req.setAttribute("cpage", cpage);
		req.setAttribute("qStr", qStr);
		
		setRedirect(false);
		setViewPage("/board/boardList.jsp");

	}

}
