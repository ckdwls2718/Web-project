<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ajax.book.*, java.util.*, net.sf.json.*"%>
<%
	BookDAO dao = new BookDAO();
	List<BookDTO> arr = dao.getPublishList();
	//List<String> publish = arr.stream().map(e -> e.getPublish()).collect(Collectors.toList());
	JSONArray jsonArr = JSONArray.fromObject(arr);
%>
<%=jsonArr%>
    

