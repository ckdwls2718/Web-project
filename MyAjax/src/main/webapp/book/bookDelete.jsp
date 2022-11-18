<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8" import="ajax.book.*"%>
    
<%
	String isbn = request.getParameter("isbn");
	BookDAO dao = new BookDAO();
	
	int n = dao.deleteBook(isbn);
	
%>
<result>
	<num><%=n%></num>
</result>