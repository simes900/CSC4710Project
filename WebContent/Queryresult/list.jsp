<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet"
type="text/css">
</head>
<body>
		<div id="header">
	<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
	</div>

<h3 align="center"> Paper Table </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>paper id</th>
		<th>title</th>
		<th>abstract</th>
		<th>pdf file</th>
	</tr>
<c:forEach items="${PaperList}" var="paper">
	<tr>
		<td>${paper.getPaperId() }</td>
		<td>${paper.getTitle() }</td>
		<td>${paper.getAbstractText() }</td>
		<td>${paper.getPdf() }</td>
	</tr>
</c:forEach>
</table>
<h3 align="center"> Authors List </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>email</th>
		<th>last name</th>
		<th>first name</th>
		<th>affiliation</th>
	</tr>
<c:forEach items="${AuthorList}" var="author">
	<tr>
		<td>${author.getEmail() }</td>
		<td>${author.getLastName() }</td>
		<td>${author.getFirstName() }</td>
		<td>${author.getAffiliation() }</td>
	</tr>
</c:forEach>
</table>
<h3 align="center"> PCMember List </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>email</th>
		<th>name</th>
	</tr>
<c:forEach items="${PcmemberList}" var="pcmember">
	<tr>
		<td>${pcmember.getEmail() }</td>
		<td>${pcmember.getName() }</td>
	</tr>
</c:forEach>
</table>

<h3 align="center"> Review List </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>Report Id</th>
		<th>submission date</th>
		<th>Comment</th>
		<th>Recommendation</th>
		<th>Paper id</th>
		<th>Email</th>
	</tr>
	
<c:forEach items="${ReviewList}" var="review">
	<tr>
		<td>${review.getReportid() }</td>
		<td>${review.getDate() }</td>
		<td>${review.getComment() }</td>
		<td>${review.getRecommendation() }</td>
		<td>${review.getPaperid() }</td>
		<td>${review.getEmail() }</td>
	</tr>
</c:forEach>
</table>

<h3 align="center"> Written List </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>Paper ID</th>
		<th>Email</th>
		<th>Order of</th>
	
	</tr>
	
<c:forEach items="${WrittenList}" var="written">
	<tr>
		<td>${written.getPaperId() }</td>
		<td>${written.getEmail() }</td>
		<td>${written.getOrderOf() }</td>
	
	</tr>
	
</c:forEach>
</table>


</body>
</html>