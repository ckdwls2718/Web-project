<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/top.jsp"/>

<div class="container">
	<h1>Index</h1>
	<p>
		<%=request.getAttribute("msg") %>
	<div style="color:tomato;font-size:2em">
	el 표현식을 이용해서 출력할 수 있다.
	${msg}
	</div>
	</p>
	
</div>

<jsp:include page="/foot.jsp"/>