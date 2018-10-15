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
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">


<title>Insert title here</title>

<style>
input[type="radio"] {
   width: 1.5em;
    height: 1.5em;
    margin-left:5%;
    //border:1px solid blue;
}

form {

//border:1px solid blue;
margin-bottom:2.5%;
}

.modType {
display:inline-block;
font-size:1.5em;
//border:1px solid yellow;
}

#modTypeButtonWrapper {
display:none;


}

.test {
border:1px solid orange;

}

#subButton {

display:block;
margin:auto;
margin-top:3%;
display:none;

}

table{

margin:auto;

}

.fieldWrapper {
//border:1px solid orange;

}

.fieldWrapper{
//border:1px solid black;
//background:lightPink;
display:none;
margin-bottom:2.5%;
}

.inputFieldWrapper {

display:none;

}

.displayTable{
display:none;



}

h3 {

font-size:1.3em;
text-transform:uppercase;
}



.addAuthorButton{

color:red;
background:lightBlue;
border:2px solid black;
cursor:pointer;

}



</style>


</head>
<body>
<script>

var isInsert = false;
var isDelete = false;
var isUpdate = false;

function changeInputType(inputSelect){
	window[inputSelect] = true;
}

function undoInputType(a,b){
	window[a] = false;
	window[b] = false;
}

function showModType(){
document.getElementById("modTypeButtonWrapper").style.display = "block";	

}

var pap = document.getElementById("paperInputFieldWrapper");
var pc = document.getElementById("pcmemberInputFieldWrapper");
var rev = document.getElementById("reviewInputFieldWrapper");

function showInsertFields(){
	document.getElementById("pcmemberDeleteFieldWrapper").style.display = "none";
	document.getElementById("paperDeleteFieldWrapper").style.display = "none";
	document.getElementById("reviewDeleteFieldWrapper").style.display = "none";
	
	document.getElementById("paperInputFieldWrapper").style.display = "block";
	document.getElementById("pcmemberInputFieldWrapper").style.display = "block";
	document.getElementById("reviewInputFieldWrapper").style.display = "block";
	
}

function showDeleteFields(){
	document.getElementById("paperInputFieldWrapper").style.display = "none";
	document.getElementById("pcmemberInputFieldWrapper").style.display = "none";
	document.getElementById("reviewInputFieldWrapper").style.display = "none";
	
	document.getElementById("pcmemberDeleteFieldWrapper").style.display = "block";
	document.getElementById("paperDeleteFieldWrapper").style.display = "block";
	document.getElementById("reviewDeleteFieldWrapper").style.display = "block";
	
	

	
}

function showUpdateFields(){
	document.getElementById("pcmemberDeleteFieldWrapper").style.display = "block";
	document.getElementById("paperDeleteFieldWrapper").style.display = "block";
	document.getElementById("reviewDeleteFieldWrapper").style.display = "block";
	document.getElementById("paperInputFieldWrapper").style.display = "block";
	document.getElementById("pcmemberInputFieldWrapper").style.display = "block";
	document.getElementById("reviewInputFieldWrapper").style.display = "block";
	
}

function showInsertType(elem, elem2){
	document.getElementById("paperFieldsWrapper").style.display = "none";
	document.getElementById("pcmemberFieldWrapper").style.display = "none";
	document.getElementById("reviewFieldWrapper").style.display = "none";
	document.getElementById("paperTable").style.display = "none";
	document.getElementById("pcmemberTable").style.display = "none";
	document.getElementById("reviewTable").style.display = "none";
	
	document.getElementById(elem).style.display = "block";
	document.getElementById(elem2).style.display = "initial";
	
	
	
	
}


function disableDropDownMenu(){
	
	document.getElementById("paperIDdropbox").disabled = true;
	document.getElementById("paperFieldA").disabled = false;
	document.getElementById("paperFieldB").disabled = false;
	document.getElementById("paperFieldC").disabled = false;
	
	document.getElementById("paperFieldA").required = true;
	document.getElementById("paperFieldB").required = true;
	document.getElementById("paperFieldC").required = true;

	
	document.getElementById("pcmemberEmailDropbox").disabled = true;
	document.getElementById("pcmemberFieldA").disabled = false;
	document.getElementById("pcmemberFieldB").disabled = false;
	
	document.getElementById("reviewIDdropbox").disabled = true;
	document.getElementById("reviewFieldA").disabled = false;
	document.getElementById("reviewFieldB").disabled = false;
	document.getElementById("reviewFieldC").disabled = false;
	document.getElementById("reviewFieldD").disabled = false;
	document.getElementById("reviewFieldE").disabled = false;
	
	document.getElementById("reviewFieldD").required = true;
	document.getElementById("reviewFieldE").required = true;
	
	
	document.getElementById("authorFieldA").disabled = false;
	document.getElementById("authorFieldB").disabled = false;
	document.getElementById("authorFieldC").disabled = false;
	document.getElementById("authorFieldD").disabled = false;
	document.getElementById("authorFieldE").disabled = false;
	
	
	document.getElementById("authorHeading").style.display = "block";
	document.getElementById("authorTable").style.display = "initial";
	
}

function enableDropDownMenu(){
	
	document.getElementById("pcmemberEmailDropbox").disabled = false;
	
	document.getElementById("paperFieldA").disabled = true;
	document.getElementById("paperFieldB").disabled = true;
	document.getElementById("paperFieldC").disabled = true;
	
	document.getElementById("paperFieldA").required = false;
	document.getElementById("paperFieldB").required = false;
	document.getElementById("paperFieldC").required = false;
	
	document.getElementById("paperIDdropbox").disabled = false;
	document.getElementById("pcmemberFieldA").disabled = true;
	document.getElementById("pcmemberFieldB").disabled = true;
	
	document.getElementById("reviewIDdropbox").disabled = false;
	document.getElementById("reviewFieldA").disabled = true;
	document.getElementById("reviewFieldB").disabled = true;
	document.getElementById("reviewFieldC").disabled = true;
	document.getElementById("reviewFieldD").disabled = true;
	document.getElementById("reviewFieldE").disabled = true;
	
	document.getElementById("authorFieldA").disabled = true;
	document.getElementById("authorFieldB").disabled = true;
	document.getElementById("authorFieldC").disabled = true;
	document.getElementById("authorFieldD").disabled = true;
	document.getElementById("authorFieldE").disabled = true;
	
	document.getElementById("authorHeading").style.display = "none";
	document.getElementById("authorTable").style.display = "none";
	
}

function enableAll(){
	document.getElementById("paperIDdropbox").disabled = false;
	document.getElementById("paperFieldA").disabled = false;
	document.getElementById("paperFieldB").disabled = false;
	document.getElementById("paperFieldC").disabled = false;
	
	document.getElementById("paperFieldA").required = false;
	document.getElementById("paperFieldB").required = false;
	document.getElementById("paperFieldC").required = false;
	
	document.getElementById("pcmemberEmailDropbox").disabled = false;
	document.getElementById("pcmemberFieldA").disabled = false;
	document.getElementById("pcmemberFieldB").disabled = false;
	
	document.getElementById("reviewIDdropbox").disabled = false;
	document.getElementById("reviewFieldA").disabled = false;
	document.getElementById("reviewFieldB").disabled = false;
	document.getElementById("reviewFieldC").disabled = false;
	document.getElementById("reviewFieldD").disabled = false;
	document.getElementById("reviewFieldE").disabled = false;
	
	document.getElementById("reviewFieldD").required = false;
	document.getElementById("reviewFieldE").required = false;
	
	document.getElementById("authorHeading").style.display = "none";
	document.getElementById("authorTable").style.display = "none";
	
}




function modString(str){
	

	document.getElementById("paperSubmit").value = str;
	document.getElementById("pcmembersubmit").value = str;
	document.getElementById("reviewsubmit").value = str;
}


var a = 1;

function addField(){

var div = document.createElement('table');
document.getElementById('paperInputFieldWrapper').appendChild(div);
div.id = 'authorField' + a;



div.innerHTML = 



'<tr>' +
'<th>Email</th>' +
'<th>First Name</th>' +
'<th>Last Name</th>' +
'<th>Affiliation</th>' +
'<th>Order Of</th>' +
'</tr>' +
'<tr>' +
'<td><input type="text" name="fieldinput"></td>' +
'<td><input type="text" name="fieldinput"></td>' +
'<td><input type="text" name="fieldinput"></td>' +
'<td><input type="text" name="fieldinput"></td>' +
'<td><input type="text" name="fieldinput"></td>' +
'</tr>';



a++;
console.log("a: " + a);
}


function removeField(){

	if(a == 1){

		console.log("no divs to delete");
	}
else{
	var str = 'authorField' + (a - 1);

	console.log(str);
	
var elem = document.getElementById(str);
elem.parentNode.removeChild(elem);
a--;
console.log("a: " + a);
}
}

function changeField(){


while(a > 1){
if(a == 1){

		console.log("no divs to delete");
	}
else{
	var str = 'authorField' + (a - 1);

	console.log(str);
	
var elem = document.getElementById(str);
elem.parentNode.removeChild(elem);
a--;
console.log("a: " + a);
}



}


}


function changeType(updateType){
	
	
	
	
	if(updateType == 'delete'){
		document.getElementById("papDel").style.display = "block";
		document.getElementById("papUpd").style.display = "none";
		document.getElementById("pcDel").style.display = "block";
		document.getElementById("pcUpd").style.display = "none";
		document.getElementById("revDel").style.display = "block";
		document.getElementById("revUpd").style.display = "none";
	}
	else if(updateType == 'update'){
		document.getElementById("papDel").style.display = "none";
		document.getElementById("papUpd").style.display = "block";
		document.getElementById("pcDel").style.display = "none";
		document.getElementById("pcUpd").style.display = "block";
		document.getElementById("revDel").style.display = "none";
		document.getElementById("revUpd").style.display = "block";
	}
	
	
}

</script>




	<div id="header">
	<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
	</div>
	

<div class="radioButtonWrapper">
<input type="radio" name="inputoperation" value="insert" onclick="modString('insert');disableDropDownMenu();showInsertFields();changeInputType('isInsert');undoInputType('isDelete','isUpdate');showModType();"><p class="modType">Insert</p>
<input type="radio" name="inputoperation" value="delete" onclick="changeType('delete');changeField();modString('delete');enableDropDownMenu();showDeleteFields();changeInputType('isDelete');undoInputType('isInsert','isUpdate');showModType();"><p class="modType">Delete</p>
<input type="radio" name="inputoperation" value="update" onclick="changeType('update');changeField();modString('update');enableAll();showUpdateFields();changeInputType('isUpdate');undoInputType('isDelete','Insert');showModType();"><p class="modType">Update</p>
</div>

<div class="radioButtonWrapper" id="modTypeButtonWrapper">
<input type="radio" name="inputtype" value="paper" onclick='showInsertType("paperFieldsWrapper","paperTable");'><p class="modType">Paper</p>
<input type="radio" name="inputtype" value="pcmember" onclick='changeField();showInsertType("pcmemberFieldWrapper","pcmemberTable");'><p class="modType">PC Member</p>
<input type="radio" name="inputtype" value="review" onclick='changeField();showInsertType("reviewFieldWrapper","reviewTable");'><p class="modType">Review</p>
</div>



<div class="fieldWrapper" id="paperFieldsWrapper">
<form id="paperModify" action="<c:url value='/processmodify'/>" method="post">
<div class="inputFieldWrapper" id="paperDeleteFieldWrapper">
<p id="papDel">Select paper id to be deleted</p>
<p id="papUpd">Select paper id to be updated</p>
<input id="paperSubmit" type="hidden" name="inputtype" value="">
<input type="hidden" name="inputtype" value="paper">
<select id="paperIDdropbox" name="primarykey" required>
<option></option>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
//String sql ="SELECT * FROM pcmember";

String sql = "SELECT paperid FROM paper";

resultSet = null;
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<option value="<%=resultSet.getString("paperid")%>"> <%=resultSet.getString("paperid") %></option>
<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</select>

</div>

<div class="inputFieldWrapper" id="paperInputFieldWrapper">

<h3>Paper fields</h3>


<table>
<tr>
<th>Title</th>
<th>Abstract</th>
<th>PDF</th>
</tr>

<tr>

<td><input id="paperFieldA" type="text" name="fieldinput"></td>
<td><input id="paperFieldB" type="text" name="fieldinput"></td>
<td><input id="paperFieldC" type="text" name="fieldinput"></td>

</tr>

</table>

<h3 id="authorHeading">Author fields</h3>

<table id="authorTable">
<tr>
<th>Email</th>
<th>First Name</th>
<th>Last Name</th>
<th>Affiliation</th>
<th>Order Of</th>
</tr>

<tr>

<td><input id="authorFieldA" type="text" name="fieldinput"></td>
<td><input id="authorFieldB" type="text" name="fieldinput"></td>
<td><input id="authorFieldC" type="text" name="fieldinput"></td>
<td><input id="authorFieldD" type="text" name="fieldinput"></td>
<td><input id="authorFieldE" type="text" name="fieldinput"></td>

</tr>
<tr class="authorButtonRow"></tr>
<tr>
<td class="addAuthorButton" onclick="addField();">Add an Author</td>
<td class="addAuthorButton" onclick="removeField();">Remove an author</td>
</tr>

</table>








</div>
</form>

<button type="submit" form="paperModify" value="Submit">Submit</button>
</div>





<div class="fieldWrapper" id="pcmemberFieldWrapper">
<form id="pcmemberModify" action="<c:url value='/processmodify'/>" method="post">
<div class="inputFieldWrapper" id="pcmemberDeleteFieldWrapper">
<p id="pcDel">Select pc member to be deleted</p>
<p id="pcUpd">Select pc member to be updated</p>
<input id="pcmembersubmit" type="hidden" name="inputtype" name="inputtype" value="">
<input type="hidden" name="inputtype" value="pcmember">
<select id="pcmemberEmailDropbox" name="primarykey" required>
<option></option>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
//String sql ="SELECT * FROM pcmember";

String sql = "SELECT email FROM pcmember";

resultSet = null;
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<option value="<%=resultSet.getString("email") %>"> <%=resultSet.getString("email") %></option>
<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</select>

</div>


<div class="inputFieldWrapper" id="pcmemberInputFieldWrapper">
<h3>PC Member fields</h3>

<input type="hidden" name="inputtype" value="insert pcmember">
<table>
<tr>
<th>Email</th>
<th>Name</th>

</tr>
<tr>
<td><input id="pcmemberFieldA" type="text" name="fieldinput"></td>
<td><input id="pcmemberFieldB" type="text" name="fieldinput"></td>

</tr>
</table>
</div>
</form>
<button type="submit" form="pcmemberModify" value="Submit">Submit</button>
</div>






<div class="fieldWrapper" id="reviewFieldWrapper">
<form id="reviewModify" action="<c:url value='/processmodify'/>" method="post">
	<div class="inputFieldWrapper" id="reviewDeleteFieldWrapper">
		<p id="revDel">Select a review to be deleted</p>
		<p id="revUpd">Select a review to be updated</p>
		<input id="reviewsubmit" type="hidden" name="inputtype" value="">
		<input type="hidden" name="inputtype" value="review">
		<select id="reviewIDdropbox" name="primarykey" required>
		
		<option></option>
		<%
		try{ 
			connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
			statement=connection.createStatement();
			//String sql ="SELECT * FROM pcmember";
			String sql = "SELECT reportid FROM review ORDER BY reportid";
			resultSet = null;
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
			%>
				<option value="<%=resultSet.getString("reportid") %>"> <%=resultSet.getString("reportid") %></option>
			<% 
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
		</select>
	
	</div>

	




<div class="inputFieldWrapper" id="reviewInputFieldWrapper">
<h3>Review Table fields</h3>

<input type="hidden" name="inputtype" value="insert review">
<table>
<tr>
<th>Date</th>
<th>Comment</th>
<th>Recommendation</th>
<th>Paper ID</th>
<th>PC Member</th>

</tr>
<tr>
<td><input id="reviewFieldA" type="text" name="fieldinput"></td>
<td><input id="reviewFieldB" type="text" name="fieldinput"></td>
<td><select id="reviewFieldC"  name="fieldinput">
	<option value=""></option>
	<option value="A">Accept</option>
	<option value="R">Reject</option>
</select>

<td>
<select id="reviewFieldD" name="fieldinput">
<option value=""></option>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql = "SELECT paperid FROM paper WHERE paperid NOT IN (SELECT paperid FROM review)"
+ "UNION SELECT paperid FROM review GROUP BY paperid HAVING COUNT(email) < 3 ORDER BY paperid";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<option value="<%=resultSet.getString("paperid") %>"> <%=resultSet.getString("paperid") %></option>
<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</select>
</td>
<td>
<select id="reviewFieldE" name="fieldinput">
<option value=""></option>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
//String sql ="SELECT * FROM pcmember";

String sql = "SELECT email FROM pcmember WHERE email NOT IN (SELECT email FROM review) "
+ "UNION SELECT email FROM review GROUP BY email HAVING COUNT(email) < 5 ORDER BY email";

resultSet = null;
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<option value="<%=resultSet.getString("email") %>"> <%=resultSet.getString("email") %></option>
<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</select>
</td>

</tr>
</table>
</div>
</form>

<button type="submit" form="reviewModify" value="Submit">Submit</button>

</div>












<table class="displayTable" id="paperTable">
<tr>


<th>Paper ID</th>
<th>Title</th>
<th>Abstract</th>
<th>PDF</th>

</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM paper";



resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>

<tr>
<td><%=resultSet.getInt("paperid")%></td>
<td><%=resultSet.getString("title")%></td>
<td><%=resultSet.getString("abstract")%></td>
<td><%=resultSet.getString("pdf")%></td>


</tr>



<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>

</table>


<table class="displayTable" id="pcmemberTable">
<tr>
<th>Email</th>
<th>Name</th>


</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM pcmember";



resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>

<tr>
<td><%=resultSet.getString("email")%></td>
<td><%=resultSet.getString("name")%></td>

</tr>



<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>

</table>



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
}
%>

</table>


	

</body>
</html>