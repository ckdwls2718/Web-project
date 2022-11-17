<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../css/layout.css">
<script type="text/javascript" src="../js/userCheck.js"></script>    
<% 	
	request.setCharacterEncoding("utf-8");
	String method= request.getMethod();
	//out.println("method : "+method+"<br>");
	
	//get방식일때는 입력폼을 보여주고
	//post 방식일때는 아이디ㅣ 사용 가능 여부 결과를 보여주자
	if(method.equalsIgnoreCase("get")){
%>
<div class="container m2" style="margin-top:2em">
	<form name="idf" action="idCheck.jsp" method="post">
		<label for="userid">아이디</label>
		<input type="text" name="userid" id="userid" placeholder="User ID" autofocus="autofocus">
		<button type="button" onclick ="id_check()">확 인</button>
	</form>
</div>
<% 
	} else {
		//post 방식이라면 => userid값 받아서 사용가능 여부 알려줌
		String userid = request.getParameter("userid");
		%>
<jsp:useBean id="userDao" class="user.model.UserDAO" scope="session"/>
		<%
		boolean isUse = userDao.idCheck(userid);
		
		if(isUse){
			%>
			<div class="container m2">
				<h3>ID로 [<span style='color:red; font-weight:bold'><%=userid %></span>]을 사용할 수 있습니다.</h3>
				<button onclick="setId('<%=userid%>')">닫    기</button>
			</div>
			<%
		} else {
			%>
			<div class="container m2">
				<h3>ID로 [<span style='color:red; font-weight:bold'><%=userid %></span>]는 이미 사용중입니다.</h3>
			</div>
			
<div class="container m2" style="margin-top:2em">
	<form name="idf" action="idCheck.jsp" method="post">
		<label for="userid">아이디</label>
		<input type="text" name="userid" id="userid" placeholder="User ID" autofocus="autofocus">
		<button type="button" onclick ="id_check()">확 인</button>
	</form>
</div>
			<%
		}
	}

%>