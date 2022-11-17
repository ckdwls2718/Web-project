<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%	
	//화면을 정상적으로 띄우기 위해
	response.setStatus(200);
%>
<script>
	alert('<%=exception.getMessage()%>');
	history.back();
</script>