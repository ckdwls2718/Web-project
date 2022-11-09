<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%
	//단일 값 받아오기 (name, passwd, gender, photo, job, intro, secret)
	//String getParameter("파라미터이름");
	
	//다중 값 받아오기 (hobby, lang)
	//String[] getParameterValues("파라미터이름");
	
	

%>
<ul>
	<li>이름 : <%=request.getParameter("userid") %></li>
	<li>비밀번호 : <%=request.getParameter("passwd") %></li>
	<li>성별 : <%=request.getParameter("gender") %></li>
	<li>파일명 : <%=request.getParameter("photo") %></li>
	<li>직업 : <%=request.getParameter("job") %></li>
	<li>자기소개 : <%=request.getParameter("intro") %></li>
	<li>비밀 : <%=request.getParameter("secret") %></li>
	<li>취미 : <%=Arrays.toString(request.getParameterValues("hobby")) %></li>
	<li>사용가능언어 : <%=Arrays.toString(request.getParameterValues("lang"))%></li>
</ul>

<%
	String server = request.getServerName();
	int port = request.getServerPort();
	StringBuffer buf= request.getRequestURL();
	String url = buf.toString();
	String uri = request.getRequestURI();
	String myctx = request.getContextPath();
	String queryString = request.getQueryString();
	String cip = request.getRemoteAddr();
	String protocol = request.getProtocol();
	String str = request.getServletPath();
%>
<h2>서버 도메인명: <%=server %></h2>
<h2>서버 포트번호: <%=port %></h2>
<h2>요청 URL     : <%=url %></h2>
<h2>요청 URI     : <%=uri %></h2>
<h2>컨텍스트명   : <%=myctx %></h2>
<h2>쿼리스트링   : <%=queryString %></h2>
<h2>클라이언트IP : <%=cip %></h2>
<h2>프로토콜     : <%=protocol %></h2>
<h2>서블릿 주소  : <%=str %></h2>