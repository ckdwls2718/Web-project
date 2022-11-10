<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그아웃 처리방법
	//[1] 세션에서 특정변수(키)를 제거하는 방법
	//session.removeAttribute("loginUser");
	
	//[2] 세션에 저장된 모든 변수를 제거하는 방법 => 권장
	session.invalidate();
	response.sendRedirect("../index.jsp");
	
	//세션 지속시간 변경방법 tomcat의 web.xml에서 session검색 후 숫자 변경 (분 단위)
%>