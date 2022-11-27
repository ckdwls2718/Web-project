<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${message ne null}">
	<script>
		alert('${message}');
		location.href='${loc}';
	</script>
</c:if>
<c:if test="${message eq null }">
	<script>
		location.href='${loc}';
	</script>
</c:if>