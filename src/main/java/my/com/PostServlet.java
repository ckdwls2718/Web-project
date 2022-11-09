package my.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//post방식은 요청 entity body에 데이터를 포함시켜 전송시킨다. url에 데이터유출이 없음
@WebServlet("/PostTest")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		//이미지나 동영상파일
		//ServletOutputStream sos = res.getOutputStream();
		
		//post방식일때 한글처리
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String userid = req.getParameter("userid");
		out.println("<h1>PostServlet</h1>");
		out.println("<h2>Post방식의 요청을 처리할 때는 doPost()메서드를 오버라이드 합니다.</h2>");
		out.println("<h3> name : "+name+"</h3>");
		out.println("<h3> userid : "+userid+"</h3>");
		out.close();
	}

}
