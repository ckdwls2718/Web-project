package memo.servlet;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import memo.model.MemoDAO;



@WebServlet("/MemoDelete")
public class MemoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 1. 삭제할 글 번호 받아오기
		String idx = req.getParameter("idx");
		// 2. 유효성 체크 (idx값이 null이거나 빈문자열)
		if (idx == null || idx.isEmpty()) {
			res.sendRedirect("MemoList");
			return;
		}
		// 3. MemoDAO의 deleteMemo(idx)호출
		MemoDAO dao = new MemoDAO();
		try {
			int n = dao.deleteMemo(Integer.parseInt(idx));
			String loc = (n > 0) ? "MemoList" : "memo/input.html";
			out.println("<script>");
			out.println("location.href='" + loc + "'");
			out.println("</script>");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.println("error : " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("error : " + e.getMessage());
		}
		out.close();
	}

}
