<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, common.util.*"%>
<% 
	String phone = request.getParameter("phone");

	Connection con = DBUtil.getCon();
	
	String sql="select * from pizza_user where phone=?";
	
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, phone);
	
	ResultSet rs = ps.executeQuery();
	
	int idx=0;
	String name="",addr="";
	
	while(rs.next()){
		idx = rs.getInt("idx");
		name = rs.getString("name");
		addr = rs.getString("addr");
	}
	if(rs!=null) rs.close();
	if(ps!=null) ps.close();
	if(con!=null) con.close();
%>

<result>
	<idx><%=idx%></idx>
	<name><%=name%></name>
	<addr><%=addr%></addr>
</result>
