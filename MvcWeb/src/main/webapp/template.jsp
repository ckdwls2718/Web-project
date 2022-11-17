<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1 style="color:red">
	msg : <%= request.getAttribute("msg") %>
</h1>
<p>
${msg}
</p>