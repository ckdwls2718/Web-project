<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<jsp:include page="/top.jsp"/>

<div class="container">
	<h1>Board 글보기</h1>
	<br>
	<a href="boardWrite.do">글쓰기</a> | <a href="boardList.do">글목록</a>
	<br><br>
	<table border="1" style="width:90%; margin:auto">
		<tr>
			<td width="20%">글번호</td>
			<td width="30%">${board.num}</td>
			<td width="20%">작성일</td>
			<td width="30%"><fmt:formatDate value="${board.wdate}" pattern="yy-MM-dd HH:mm"/></td>
		</tr>
		<tr>
			<td width="20%">글쓴이</td>
			<td width="30%">${board.userid}</td>
			<td width="20%">첨부파일</td>
			<td width="30%">
			
			<a href="upload/${board.filename}" download>${board.filename}</a> 
			
			[${board.filesize} bytes]</td>
		</tr>
		<tr>
			<td width="20%">제목</td>
			<td colspan="3">${board.subject}</td>
		</tr>
		<tr>
			<td width="20%">글내용</td>
			<td colspan="3" style="height:300px">${board.content}</td>
		</tr>
		<tr>
		
			<td colspan="4">
				<a href="boardList.do">글목록</a> | 
				<a href="#" onclick="goEdit()">수정</a> | 
				<a href="javascript:goDel()">삭제</a>
			</td>
		</tr>
	</table>
</div>
<!-- 수정 또는 삭제를 위한 form ------------------------------------------------->
<form name="bf" id="bf">
	<input type="hidden" name="num" id="num" value="${board.num}">
</form>
<!--  ------------------------------------------------->

<script>
	function goEdit(){
		bf.action="boardEditForm.do";
		bf.method="post";
		bf.submit();
	}
	
	function goDel(){
		bf.action="boardDel.do";
		bf.method="post";
		bf.submit();
	}

</script>

<jsp:include page="/foot.jsp"/>