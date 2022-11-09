<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello</title>
<style>
	.time{
		color:maroon;
	}
</style>
</head>
<body>
	<h1>Hello JSP</h1>
	<h2>안녕 쟈스피</h2>
	<hr color="red">
	<%
	//이 안에서는 자바코드를 자유 자재로 작성해도 돼요
		java.util.Date today=new java.util.Date();
		String str=today.toString();
		
		out.println("<h2 class='time'>"+str+"</h2>");
		//out: PrintWriter타입의 내장객체. 브라우저와 노드 연결됨
	%>
	
</body>
</html>