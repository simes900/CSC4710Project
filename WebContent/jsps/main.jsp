<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>main</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	
	<link rel="stylesheet" type="text/css" href="../css/style.css">

	
  </head>
  
  <body>

	<div id="header">
	<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
	</div>
			
	



    <h1>Welcome to the demo</h1>
    <button type="button" onclick="location = '<c:url value='/createtables'/>'">Initalize DB</button>
	
  </body>
</html>
