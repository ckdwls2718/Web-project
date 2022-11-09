package memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.model.*;


@WebServlet("/MemoEdit")
public class MemoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = res.getWriter();
		
//		MemoEditFormServlet작성
//		url매핑=> MemoEditForm 
//
//		1. 수정할 글 번호 받아 오기
		String idx = req.getParameter("idx");
//
//		2. 유효성 체크 -> redirect MemoList로 이동
		if(idx == null || idx.isBlank()) {
			res.sendRedirect("MemoList");
			return;
		}
//
//		3. MemoDAO의 selectMemo(idx)호출
//		==> MemoVO받아서 req에 저장
//		키값 "memo"
		MemoDAO dao = new MemoDAO();
		
		try {
			MemoVO vo =dao.selectMemo(Integer.parseInt(idx));
			
			req.setAttribute("memo", vo);
			
//			4. forward로 edit.jsp로 이동시킨다.
			String viewPage = "memo/edit.jsp";
			
			RequestDispatcher disp = req.getRequestDispatcher(viewPage);
			
			disp.forward(req, res);
			
		} catch(SQLException e) {
			e.printStackTrace();
			out.println("error : "+e.getMessage()+"<br>");
		}
		
//		edit.jsp에서는 req에서 저장된 "memo"를 꺼내서
//		형변환한다. out.println(memo)
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		String idx = req.getParameter("idx");
		String name = req.getParameter("name");
		String msg = req.getParameter("msg");
		if(idx==null || name == null || msg ==null || idx.isBlank() || name.isBlank() || msg.isBlank()) {
			res.sendRedirect("MemoList");
			return;
		}
		
		MemoVO vo = new MemoVO(Integer.parseInt(idx),name,msg,null);
		
		MemoDAO dao = new MemoDAO();
		
		try {
			int n = dao.updateMemo(vo);
			String str = (n>0) ? "글 수정 성공" : "글 수정 실패";
			String loc = (n>0) ? "MemoList" : "MemoEdit?idx="+vo.getIdx();
			
			out.println("<script>");
			out.println("alert('"+str+"')");
			out.println("location.href='"+loc+"'");
			out.println("</script>");
			
		} catch(SQLException e) {
			e.printStackTrace();
			out.println("error : "+e.getMessage()+"<br>");
		}
		
	}

}
