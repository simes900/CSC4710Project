<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException" %>
<% 
String id = request.getParameter("userId");
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "sampledb";
String userId = "john";
String password = "pass1234";

if(request.getAttribute("message") == null){
	String message = " ";
	System.out.println(message);
	request.setAttribute("message", message);
}

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
ResultSet resultSet2 = null;
%>


<html>
<head>
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
<style>

.formWrapper {
	border:1px solid black;
	text-align:left;
	display:inline-block;
	padding:10px;
	margin-top:3%;
}

#subButton {
display:block;
margin:auto;
margin-top:2%;

}

form {

}

h1 {
 font-size:1.3em;
 color:red;
 text-align:left;

}

table {
border:1px solid black;

}

table th,td{
padding: 5px;
}

</style>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

	<div id="header">
	<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
	</div>

<h1><%= request.getAttribute("message") %></h1>


<div class="formWrapper">
<h2>list of All Reviews:</h2>
<table>
<tr>
<th>Report ID</th>
<th>SDate</th>
<th>Comment</th>
<th>Recommendation</th>
<th>Paper ID</th>
<th>Email</th>
</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM review";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>

<tr>
<td><%=resultSet.getInt("reportid")%></td>
<td><%=resultSet.getString("sdate")%></td>
<td><%=resultSet.getString("comment")%></td>
<td><%=resultSet.getString("recommendation")%></td>
<td><%=resultSet.getInt("paperid")%></td>
<td><%=resultSet.getString("email")%></td>

</tr>



<% 
}

} catch (Exception e) {
e.printStackTrace();
}finally{
	if(resultSet != null){
		try{resultSet.close();}catch(SQLException e){}
	}
	if(resultSet2 !=null){
		try{resultSet2.close();}catch(SQLException e){}
	}
	if(statement != null){
		try{statement.close();}catch(SQLException e){}
	}
	if(connection != null){
		try{connection.close();}catch(SQLException e){}
	}
	
}
%>

</table>
</div>






</body>
</html>