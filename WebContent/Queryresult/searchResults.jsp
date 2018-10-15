<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>

<style>

table {
border:1px solid purple;
margin:auto;


}

td {
font-size:1.5em;
text-align:left;
border:1px solid black;
padding:15px;

}

h2 {

font-size:1.5em;

}


</style>



</head>
<body>
	<div id="header">
	<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
	</div>




<% ArrayList<String> results = (ArrayList<String>) request.getAttribute("message"); %>
<h2><%=results.get(0)%></h2>

<table>
<% 

for(int i = 1; i < results.size(); i = i + 2){
	
	%>
	<tr>
	<td><%=results.get(i)%></td><td><%=results.get(i + 1)%></td>
	</tr>
	
	
	
	<%
	
}


%>


</table>
	
	
	
</body>
</html>