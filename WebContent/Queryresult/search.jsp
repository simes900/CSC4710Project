<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">

<style>
#searchFormWrapper{
//border:1px solid purple;
margin-top:5%;
vertical-align:top;

}

.searchField {
//border:1px solid orange;
display:inline-block;
margin-right:2.5%;
vertical-align:top;
}

h2 {
font-size:1.5em;

}


#numberOfSearchField input{

display:block;
margin:auto;

}


.addAuthorButton {
border:1px solid black;
cursor:pointer;
background:lightBlue;
}

#blankInput {
visibility:hidden;

}

#numberOfAuthors {
cursor:pointer;

}

#rankingOfAuthor{
cursor:pointer;

}


.toggleButton {

border:1px solid black;
background:lightBlue;
}

form{
//border:1px solid pink;

}

#allAcceptedPapers {
//border:1px solid black;
padding:0.5% 1.5%;
cursor:pointer;

}

#allPapersButton {
width:20%;
cursor:pointer;
margin-bottom:1.5%;

}

#pcMemberFormsWrapper{
visibility:hidden;

}

#pcMemberAssignedWrapper{
border:3px solid black;
padding:1% 4%;
}


.pcmemberNumberAssignedPapersFont{
font-size:1.5em;


}



</style>


</head>
<body onload="hideFields();">
<script>

	var a = 1;

function addField(type,value){
	


var div = document.createElement('div');
document.getElementById('namesOfAuthors').appendChild(div);
div.id = 'authorField' + a;



div.innerHTML = 

'<p>' + type + '</p><input name="names">';

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

var isPaper = false;
var isPCmember = false;
var rejectedBy = false;

function changeType(){
	var type = document.getElementById('paperOrPcmemberDropBox').value;
	console.log("hello world");
	
	
	
	if(type == "paper"){
		
		console.log(rejectedBy);
		
		if(rejectedBy){
			isPaper = true;
			isPCmember = false;
			makePcMember();
			showPaperFields();
			document.getElementById('numberOfAuthors').style.visibility = "hidden";
			document.getElementById('rankingOfAuthor').style.visibility = "hidden";
			
		}
		else{
			isPaper = true;
			isPCmember = false;
			makePaper();
			showPaperFields();
		}
		
		
		
		
		document.getElementById("pcMemberFormsWrapper").style.visibility = "hidden";
		
	}
	else if(type == "pcmember"){
		
		
		
		isPaper = false;
		isPCmember = true;
		
		
		hidePaperFields();
		
		
			document.getElementById("pcMemberFormsWrapper").style.visibility = "visible";
	
	}
	

	
	else{
		document.getElementById("pcMemberFormsWrapper").style.visibility = "hidden";
		isPaper = false;
		isPCmember = true;
		hideFields();
		document.getElementById('blankInput').style.display = "block";
		hidePaperFields();
		
	}
	
	
}


	function hideFields(){
		hidePaperFields();
		document.getElementById('auth01').style.display = "none";
		document.getElementById('auth02').style.display = "none";
		document.getElementById('auth03').style.display = "none";
		document.getElementById('auth04').style.display = "none";
		
		document.getElementById('pc01').style.display = "none";
		document.getElementById('pc02').style.display = "none";
		document.getElementById('pc03').style.display = "none";
		document.getElementById('pc04').style.display = "none";
		
	}

	
	
	
function makePaper(){
	document.getElementById('auth02').disabled = false;
	document.getElementById('pc02').disabled = true;
		document.getElementById('pc01').style.display = "none";
		document.getElementById('pc02').style.display = "none";
		document.getElementById('pc03').style.display = "none";
		document.getElementById('pc04').style.display = "none";
		document.getElementById('auth01').style.display = "block";
		document.getElementById('auth02').style.display = "block";
		document.getElementById('auth03').style.display = "block";
		document.getElementById('auth04').style.display = "block";
		document.getElementById('blankInput').style.display = "none";
		
	}
	
	function makePcMember(){
		document.getElementById('pc02').disabled = false;
		document.getElementById('auth02').disabled = true;
		document.getElementById('auth01').style.display = "none";
		document.getElementById('auth02').style.display = "none";
		document.getElementById('auth03').style.display = "none";
		document.getElementById('auth04').style.display = "none";
		document.getElementById('pc01').style.display = "block";
		document.getElementById('pc02').style.display = "block";
		document.getElementById('pc03').style.display = "block";
		document.getElementById('pc04').style.display = "block";
		document.getElementById('blankInput').style.display = "none";
		
	}
	
	
	function changeCondition(){
		
	
		var type = document.getElementById('searchConditionDropBox').value;
		console.log("hello world");
		if((type == "rejectedby" || type =="acceptedby") && isPaper){
			makePcMember();
			document.getElementById("numberOfAuthorInput").disabled = true;
			document.getElementById("authorRankingInput").disabled = true;
			rejectedBy = true;
			document.getElementById("numberOfAuthors").style.visibility = "visible";
			document.getElementById("rankingOfAuthor").style.visibility = "hidden";
			
		}
		else if(type == "where" && isPaper){
			document.getElementById("numberOfAuthorInput").disabled = true;
			document.getElementById("authorRankingInput").disabled = true;
			makePaper();
			document.getElementById("numberOfAuthors").style.visibility = "visible";
			document.getElementById("rankingOfAuthor").style.visibility = "visible";
			rejectedBy = false;
		}
		
		
	}
	
	function disableNumberAndRankingsOfAuthor(elem){
		
	
		
		
		if(document.getElementById(elem).disabled == false && isPaper){
			//document.getElementById(elem).value = "1";
			document.getElementById(elem).disabled = true;
			
			
		}
		else if(isPaper){
			
			document.getElementById(elem).disabled = false;
			
		}
		
		
	}
	
	function validateAuthorNumber(){
		
		
		var x = document.forms["paperSearch"]["numberOfAuthors"].value;
		var y = document.getElementById("numberOfAuthorInput").disabled;
		console.log(x);
		if(x < a && x != "" && !rejectedBy && !y){
			alert("Number of Authors field must be equal to the number of authors listed!");
			return false;
		}
		
		
		
		
	}
	
	
	
	function validatePcMemberReviewAmount(){
		var x = document.forms["pcmemberNumberAssignedPapers"]["pcmembersassigned"].value;
		console.log(x);
		if(x < 1){
			alert("Number of Assigned Papers must be between 1 and 5!");
			return false;
		}
		
	}
	
	

	
	function hidePaperFields(){
		document.getElementById('paperFormSubmitButton').style.visibility = "hidden";
		document.getElementById('searchConditions').style.visibility = "hidden";
		document.getElementById('namesOfAuthors').style.visibility = "hidden";
		document.getElementById('numberOfAuthors').style.visibility = "hidden";
		document.getElementById('rankingOfAuthor').style.visibility = "hidden";
		
	}
	
	function showPaperFields(){
		document.getElementById('paperFormSubmitButton').style.visibility = "visible";
		document.getElementById('searchConditions').style.visibility = "visible";
		document.getElementById('namesOfAuthors').style.visibility = "visible";
		document.getElementById('numberOfAuthors').style.visibility = "visible";
		document.getElementById('rankingOfAuthor').style.visibility = "visible";
		
	}

</script>
<div id="header">
<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
</div>

<div id="searchFormWrapper">

<form id="allAcceptedPapers" action="<c:url value='/search'/>" method="post">

<input name="pcmembername" type="hidden" value="allAcceptedPapers">

</form>
<button id="allPapersButton" type="submit" form="allAcceptedPapers" value="Submit">Click Here to View All Papers Which Have Been Accepted</button>

<form id="paperSearch" action="<c:url value='/search'/>" method="post" onsubmit="return validateAuthorNumber();">


<div class="searchField" onclick="testFunction();">

<h2>LIST</h2>

</div>


<div class="searchField" onchange="changeField();changeType();">
<p>Search For</p>
<select id="paperOrPcmemberDropBox" name="paperOrPcMember" required>
<option></option>
<option value="paper">Paper(s)</option>
<option value="pcmember">PC Member(s)</option>
</select>
</div>

<div class="searchField" onchange="changeCondition();changeField();" id="searchConditions">

<p>Search Condition:</p>
<select id="searchConditionDropBox" name="whereOrRejectedBy" required>

<option value="where">WHERE</option>
<option value="rejectedby">REJECTED BY</option>
<option value="acceptedby">ACCEPTED BY</option>

</select>

</div>

<div class="searchField" id="namesOfAuthors">
<input id="blankInput" disabled>
<p id="auth01">Author Name:</p>
<input type="text" name="names" id="auth02">
<p id="pc01">PC Member Name:</p>
<input name="names" id="pc02">
<p class="addAuthorButton" id="auth03" onclick="addField('Author Name: ');">Add Another Author</p>
<p class="addAuthorButton" id="auth04" onclick="removeField();">Remove an Author</p>
<p class="addAuthorButton" id="pc03" onclick="addField('PC member Name: ');">Add Another PC Member</p>
<p class="addAuthorButton" id="pc04" onclick="removeField();">Remove a PC Member</p>
<select id="orAndDropBox" name="orAnd" required>
<option value="AND">AND</option>
<option value="or">OR</option>
</select>
</div>

<div class="searchField" id="numberOfAuthors">
<p>Where Number of Authors:<br>(click to disable)</p>
<input type="number" min="1" id="numberOfAuthorInput" name="numberOfAuthors" value="1" disabled>
<p class="toggleButton" onclick="disableNumberAndRankingsOfAuthor('numberOfAuthorInput');">Toggle enabled</p>
</div>

<div class="searchField" id="rankingOfAuthor">
<p>Author Ranking:<br>(click to disable)</p>
<input type="number" min="1" id="authorRankingInput" name="authorRank" value="1" disabled>
<p class="toggleButton"  onclick="disableNumberAndRankingsOfAuthor('authorRankingInput');">Toggle enabled</p>
</div>

</form>

<button id="paperFormSubmitButton" type="submit" form="paperSearch" value="Submit">submit</button>

<div id="pcMemberFormsWrapper">


<div class="searchField" id="pcMemberAssignedWrapper">
<form id="pcmemberNumberAssignedPapers" action="<c:url value='/search'/>" method="post" onsubmit="return validatePcMemberReviewAmount();">
<p class="pcmemberNumberAssignedPapersFont">Who are Assigned:</p>
<input type="number" min="1" max="5" id="pcMemberAmountAssigned" name="pcmembersassigned">
<p class="pcmemberNumberAssignedPapersFont">papers</p>
<input name="pcmembername" type="hidden" value="pcMemberNumberAssignedPapers">
</form>
<button type="submit" form="pcmemberNumberAssignedPapers" value="Submit">Submit</button>
</div>

<form id="pcmemberNoPaperAssigned" action="<c:url value='/search'/>" method="post">

<input name="pcmembername" type="hidden" value="pcMemberNoAssignedPapers">

</form>
<button type="submit" form="pcmemberNoPaperAssigned" value="Submit">Who Are Assigned 0 Papers</button>


<form id="pcmemberReviewMostPaper" action="<c:url value='/search'/>" method="post">

<input name="pcmembername" type="hidden" value="pcMemberReviewedMostPapers">

</form>
<button type="submit" form="pcmemberReviewMostPaper" value="Submit">who has reviewed the most papers</button>







</div>


</div>

</body>
</html>