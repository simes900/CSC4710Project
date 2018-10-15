<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<% 
String id = request.getParameter("userId");
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "sampledb";
String userId = "john";
String password = "pass1234";

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
	margin-top:1.5%;
}

#subButton {
display:block;
margin:auto;
margin-top:2%;

}

.formWrapper {

vertical-align:top;
font-size:1.3em;

}

.formWrapper h2 {
font-size:1.15em;
text-decoration:underline;
}


#reviewTable {

margin:auto;


}

h1{
margin-top:2%;
border:0px solid green;
font-size:1.5em;
margin-bottom:0%;
}

</style>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<script>

var count = 0;
var isPaper = false;
var buttonArray = [];

function validateForm() {
	console.log("count: " + count);
	console.log("isPaper: " + isPaper);
	
	if(count == 0 && !isPaper){
		alert("You must select at least 1 pc member and 1 paper!");
        return false;
	}
    
	else if (count == 0) {
        alert("You must select at least 1 PC member!");
        return false;
    }
	
	else if(!isPaper){
		alert("You must select at least 1 Paper!");
		return false;
	}
}

function paperClicked(){
	isPaper = true;
}

function KeepCount(item){
	var c = false;
	for(i = 0; i < buttonArray.length; i++){
		//console.log(buttonArray[i]);
		if(item == buttonArray[i]){
			console.log("item " + item);
			console.log("buttonarri " + buttonArray[i]);
			console.log("i: " + i);
			c = true;
			console.log(c);
			console.log("match" + count);
			buttonArray.splice(i, 1);
			count--;
			console.log("--" + count);
		
				
			
		}
	}
	
	if(count >= 3){
		console.log("alert " + count);
		alert("You can only assign 3 pc members to a paper!");
		return false;
	}
	else{
	
	
	if(!c && count != 3){
		buttonArray.push(item);
		count++;
		console.log("++" + count);
	}

	for(i = 0; i < buttonArray.length; i++){
		console.log(buttonArray[i]);
	
	}
	}

	
	
	
}


</script>
	<div id="header">
	<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
	</div>

<h1>Please select up to three PC members and 1 paper to assign them to:</h1>

<form onsubmit="return validateForm()" method="get" action="<c:url value='/assignpcmemb'/>">
<div class="formWrapper">
<h2>list of PC members:</h2>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT email FROM pcmember WHERE email NOT IN (SELECT email FROM review)"
		+ "UNION SELECT email FROM review GROUP BY email HAVING COUNT(email) < 5 ORDER BY email";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>


<input type="checkbox" name="pcmember" value="<%=resultSet.getString("email") %>" onClick="return KeepCount('<%=resultSet.getString("email")%>')"> <%=resultSet.getString("email") %> <br>



<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>



</div>

<div class="formWrapper">
<h2>list of papers: </h2>

<%
try{ 
System.out.println("close");
connection = null;
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
	String sql2 ="SELECT paperid, title FROM paper WHERE paperid NOT IN (SELECT paperid FROM review)" +
			"UNION SELECT R.paperid, P.title FROM review R, paper P WHERE R.paperid = P.paperid GROUP BY paperid HAVING COUNT(email) < 3 ORDER BY paperid";
resultSet2 = statement.executeQuery(sql2);
while(resultSet2.next()){
%>


<input type="radio" name="paper" value="<%=resultSet2.getString("paperid") %>" onclick="paperClicked()"> <%=resultSet2.getString("title") %> paperid: <%=resultSet2.getString("paperid") %> <br>



<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</div>
<input id="subButton" type="submit" value="Submit">
</form>


<table class="displayTable" id="reviewTable">
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
String sql ="SELECT * FROM review ORDER BY paperid";



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
}
%>

</table>

</body>
</html>