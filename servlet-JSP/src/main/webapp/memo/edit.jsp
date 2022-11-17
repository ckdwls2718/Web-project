<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, memo.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한줄 메모장</title>
<link rel="stylesheet" type="text/css" href="css/memo.css">
<script type="text/javascript">
	function check(){
		//유효성 체크
		if(window.document.frm.name.value == ""){
			alert("이름을 입력하세요");
			frm.name.focus();
			return;
		}
		if(frm.msg.value == ""){
			alert("메모내용을 입력하세요");
			frm.msg.focus();
			return;
		}
		if(frm.msg.value.length > 100){
			alert("100자 까지만 가능합니다");
			frm.msg.select();
			return;
		}
		frm.submit();
	}
</script>
</head>
<body>

<%
	Object obj=request.getAttribute("memo");
	MemoVO vo = (MemoVO) obj;
	
	if(vo == null){
		%>
		alert("존재하지 않는 글입니다");
		history.back();
		<%
		return;
	}
%>
<div id="wrap">
	<form name="frm" action="MemoEdit" method="post">
		<table border="1">
			<tr>
				<th colspan="2"><h1>::한줄메모장 수정::</h1></th>
			</tr>
			<tr>
				<td width="20%"><b>글번호</b></td>
				<td width="20%"><input type="text" name="idx" readonly placeholder="Index" value=<%=vo.getIdx()%>></td>
			</tr>
			<tr>
				<td width="20%"><b>작성자</b></td>
				<td width="80%"><input type="text" name="name" placeholder="Name" value=<%=vo.getName()%>></td>
			</tr>
			<tr>
				<td width="20%"><b>메모 내용</b></td>
				<td width="80%"><input type="text" name="msg" placeholder="Message" value=<%=vo.getMsg()%>></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="check()">글 수정하기</button>
					<button type="reset">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>