<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" import="ajax.book.*"%>
    
<jsp:useBean id="bookDto" class="ajax.book.BookDTO" scope="page"/>
<jsp:setProperty property="*" name="bookDto"/>
<%
 	/* String isbn = request.getParameter("isbn");
 	String title = request.getParameter("title");
 	String publish = request.getParameter("publish");
 	String price = request.getParameter("price");

	BookDTO dto = new BookDTO(isbn,title,publish,Integer.parseInt(price),null,null); */
	
	BookDAO dao = new BookDAO();
	
	int n = dao.updateBook(bookDto);
	
	request.setAttribute("n", n);
%>
{
	"result": ${n}
}
