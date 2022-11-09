<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<div class="container err">

	<h1>server error : <%=exception.getMessage() %></h1>
	<%
		exception.printStackTrace();
	%>
	<a href="javascript:history.back()">이전 페이지로 돌아감</a>
	<button onclick="history.back()">돌아가기</button>

</div>