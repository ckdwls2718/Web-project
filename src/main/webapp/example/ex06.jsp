<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

</script>
<jsp:include page="/top.jsp" />
<div class="container">
	<h1>성적 입력</h1>
	<form action="result.jsp" method="post">
	<table border="1">
		<tr>
			<td colspan="2">사번</td>
			<td ><input type="text" name="idx"></td>
		</tr>
		<tr>
			<td rowspan="3">과목</td>
			<td>Java</td>
			<td ><input type="text" name="java"></td>
		</tr>
		<tr>
			<td>Database</td>
			<td ><input type="text" name="db"></td>
		</tr>
		<tr>
			<td>JSP</td>
			<td ><input type="text" name="jsp"></td>
		</tr>
		<tr>
			<td colspan="3"><span ><button type="button">저장</button></span></td>
		<tr>
	</table>
	
	</form>
</div>
<jsp:include page="/foot.jsp" />
    