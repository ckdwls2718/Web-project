<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top" />   
<div class="container">
	<h1>MyPage</h1>
	<p>
	<h2>회원인증을 받은 사람들만 이용할 수 있어요</h2>
	<h2>이름: ${loginUser.name}</h2>
	<h2>연락처: ${loginUser.getAllHp()}</h2>
	<h2>주소: ${loginUser.allAddr}</h2>
	<!--  loginUser.getAllAddr()-->
	</p>
</div>
<c:import url="/foot" />