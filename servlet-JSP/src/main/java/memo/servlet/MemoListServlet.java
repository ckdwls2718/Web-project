package memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import memo.model.*;


@WebServlet("/MemoList")
public class MemoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = res.getWriter();
		
		//전체 메모 목록 가져오기
		MemoDAO dao = new MemoDAO();
		
		try {
			List<MemoVO> arr = dao.selectMemoAll();
			//모델을 통해 가져온 데이터를 req에 저장한 뒤 view페이지로 이동한다.
			//=> 이때 주의, redirect방식이 아니라 forward방식으로 이동해야 한다.
			//req.setAttribute(key값, value값);
			req.setAttribute("memoArr", arr);
			
			String viewPage = "memo/list.jsp";
			
			// forward 이동은 req, res도 같이 가져갈수 있다.
			RequestDispatcher disp = req.getRequestDispatcher(viewPage);
			disp.forward(req, res);
			
		} catch(Exception e) {
			e.printStackTrace();
			out.println("error: "+e.getMessage()+"<br>");
		}
		
		out.close();
	}

}
