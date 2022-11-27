<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top" />
<script type="text/javascript" src="./js/userCheck.js"></script>
<div class="container" style="height:750px; overflow: auto;">
	<h1 class="text-center mt-1">Signup</h1>
	<form name="mf" action="join" method="post">
		<!-- 날짜 : <input type="date" name ="birth">
		<span class="ck">*yyyy-mm-dd or yyyy/mm/dd</span> 
		이메일 : <input type="text" name="email"><br>
		<span class="ck">*aaa@naver.com 이메일은 알파벳문자,숫자, 하이픈@naver.com 형식</span>
		-->
		<!-- hidden data----------------------------------------- -->
		<input type="text" name="id_flag" id="id_flag" value="N">
		<!-- --------------------------------------------------- -->
		<table class="table">
			<tr>
				<td width="20%" class="m1"><b>이름</b></td>
				<td width="80%" class="m2">
				<input type="text" name="name" id="name" placeholder="Name">
				<br><span class="ck">*이름은 한글만 가능해요</span>
				</td>
			</tr>
			<tr>
				<td width="20%" class="m1"><b>아이디</b></td>
				<td width="80%" class="m2">
				<input type="text" name="userid" id="userid" placeholder="User ID">
				<button type="button" onclick="ajax_idCheck(userid.value)" class="btn btn-success">아이디 중복 체크</button>
				<br><span class="ck">*아이디는 영문자,숫자,_,!만 사용 가능해요</span>
				</td>
			</tr>
			<tr>
				<td width="20%" class="m1"><b>비밀번호</b></td>
				<td width="80%" class="m2">
				<input type="password" name="pwd" id="pwd" placeholder="Password">
				<br><span class="ck">*비밀번호는 문자,숫자,!,.포함해서 4~12자리 이내</span>
				</td>
			</tr>
			<tr>
				<td width="20%" class="m1"><b>비밀번호 확인</b></td>
				<td width="80%" class="m2">
				<input type="password" name="pwd2" id="pwd2" placeholder="Re Password">
				</td>
			</tr>
			<tr>
				<td width="20%" class="m1"><b>연락처</b></td>
				<td width="8	`0%" class="m2">
				<input type="number" name="hp1" id="hp1" placeholder="HP1" size="3" maxlength="3">-
				<input type="number" name="hp2" id="hp2" placeholder="HP2" size="4" maxlength="4">-
				<input type="number" name="hp3" id="hp3" placeholder="HP3" size="4" maxlength="4">
				<br><span class="ck">*앞자리는(010/011)중에 하나 -숫자(3~4)-숫자(4) 가능해요</span>
				</td>
			</tr>
			<tr>
				<td width="20%" class="m1"><b>우편번호</b></td>
				<td width="80%" class="m2">
				<input type="text" name="post" id="post" placeholder="Post" maxlength="5">
				<button type="button" class="btn btn-success">우편번호 찾기</button>
				</td>
			</tr>
			<tr>
				<td width="20%" class="m1"><b>주소</b></td>
				<td width="80%" class="m2">
				<input type="text" name="addr1" id="addr1" placeholder="Address1"><br>
				<input type="text" name="addr2" id="addr2" placeholder="Address2">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="m3 text-center">
					<button class="btn btn-primary" type="button" onclick="member_check()">회원가입</button>
					<button class="btn btn-danger" type="reset">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</div>
<c:import url="/foot" />
