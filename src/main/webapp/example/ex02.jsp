<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<h1>scripting element</h1>
<%--
[1] scriptlet  : <%  %>  => 여기서 선언된 변수는 지역변수
[2] declaration: <%! %>  => 여기서 선언된 변수는 멤버변수
[3] expression : <%= %>
 --%>
<h1><%=new Date().toString()%></h1>
<%!
//여기서는 멤버변수를 선언하거나 사용자정의 메서드를 구성할 수 있다.
	String global="인스턴스변수(멤버변수)";

	public String sum(int a, int b){
		return a+"+"+b+"="+(a+b);
	}

%>
<h2><%=global%></h2>
<!--  sum()호출해서 반환해주는 값 출력하기 -->
<h2><%=sum(10,20) %></h2>

<% //scriptlet태그 안에서 작성된 코드들은 서블릿의 __jspService()메서드 안에 들어간다.
	String local="지역변수";
	out.println(local);
	//C:\myjava\Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\MyWeb\org\apache\jsp\example
	//이클립스에서 서블릿 코드 생성되는 곳
%>


