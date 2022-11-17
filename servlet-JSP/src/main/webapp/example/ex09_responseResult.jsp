<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<a href="ex09_response.jsp">로그인 폼으로 돌아가기</a>
<%
String userid = request.getParameter("userid");
String pwd = request.getParameter("pwd");

// 유효성 체크
if (userid == null || pwd == null) {
   response.sendRedirect("ex09_response.jsp");
   return;
}

// 빈 문자열
if ("".equals(userid.trim()) || pwd.trim().isEmpty()) {
   response.sendError(HttpServletResponse.SC_BAD_REQUEST); // 잘못된 요청 에러
   return;
}

if (userid.equalsIgnoreCase("killer")) {
   response.sendError(response.SC_FORBIDDEN); // 접근 금지 에러
}
%>
<h1 style='color: blue'><%=userid%>님 환영합니다
</h1>