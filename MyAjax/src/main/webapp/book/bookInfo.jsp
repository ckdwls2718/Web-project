<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ajax.book.*"%>
<%	
	String isbn = request.getParameter("isbn");
	BookDAO dao = new BookDAO();
	BookDTO dto = dao.getBookInfo(isbn);
%>

<result>
	<isbn><%=dto.getIsbn()%></isbn>
	<title><%=dto.getTitle()%></title>
	<publish><%=dto.getPublish()%></publish>
	<price><%=dto.getPrice()%></price>
	<published><%=dto.getPublished()%></published>
	<bimage><%=dto.getBimage()%></bimage>
</result>