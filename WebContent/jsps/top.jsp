<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



  

<h1 style="text-align: center;">CSC4710 Project - Adam Molner and Simon Kuang</h1>
<div style="font-size: 10pt;">
	<c:choose>
		<c:when test="${empty sessionScope.session_user }">
			<a href="<c:url value='/jsps/main.jsp'/>" target="_parent">Initialize DB</a> |&nbsp;
			<a href="<c:url value='/Queryresult/list.jsp'/>" target="_parent">Query</a> |&nbsp; 
			<a href="<c:url value='/jsps/assign.jsp'/>" target="_parent">Assign three reviewers to a paper</a> |&nbsp;
			<a href="<c:url value='/modify/modify.jsp'/>" target="_parent">Modify</a> |&nbsp;
			<a href="<c:url value='/Queryresult/search.jsp'/>" target="_parent">Search</a> 	
							
		</c:when>
		<c:otherwise>
			Hello：${sessionScope.session_user.username };
			<a href="<c:url value='/jsps/item.jsp'/>" target="body">Query Result</a>&nbsp;&nbsp;
		</c:otherwise>
	</c:choose>
	
</div>


