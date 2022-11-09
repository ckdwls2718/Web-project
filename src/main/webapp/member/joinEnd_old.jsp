<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="user.model.*"%>
<%	
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	String userid = request.getParameter("userid"); 
	String pwd = request.getParameter("pwd"); 
	String hp1 = request.getParameter("hp1"); 
	String hp2 = request.getParameter("hp2"); 
	String hp3 = request.getParameter("hp3"); 
	String post = request.getParameter("post"); 
	String addr1 = request.getParameter("addr1"); 
	String addr2 = request.getParameter("addr2"); 
	
	if(name == null || userid==null || pwd == null || hp1 == null || hp2 == null || hp3 == null
			|| post == null || addr1 == null || addr2 == null
			|| name.isBlank() || userid.isBlank() || pwd.isBlank() || hp1.isBlank() || hp2.isBlank()
			|| hp3.isBlank() || post.isBlank() || addr1.isBlank() || addr2.isBlank()){
		out.println("<script>");
		out.println("alert('제대로된 값을 입력하세요');");
		out.println("location.href='join.jsp';");
		out.println("</script>");
		return;
	}
	
	UserVO vo = new UserVO(0,name,userid,pwd,hp1,hp2,hp3,post,addr1,addr2,null,0,0);
	
	UserDAO dao = new UserDAO();
	
	int n = dao.insertUser(vo);
	
	String str = (n>0) ? "회원가입 완료" : "회원가입 실패";
	String loc = (n>0) ? "../login/login.jsp" : "javascript:history.back()";
%>
<script>
	alert('<%=str%>');
	location.href='<%=loc%>';
</script>