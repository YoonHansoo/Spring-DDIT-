<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>textView.jsp</h2>
<form action="${cp}/textReq">
	<input type="text" name = "userId" value="brown"/>   <form:errors path="userVo.userId"/>  <br/>
	<input type="password" name = "pass" value="1234"/>  <form:errors path="userVo.pass"/>  <br/>
	<input type="submit" value="전송"/>
</form>

<h2>textReqJ303</h2>
<form action="${cp}/textReqJsr303">
	<input type="text" name = "userId" value="brown"/>   <form:errors path="userVo.userId"/>  <br/>
	<input type="password" name = "pass" value="1234"/>  <form:errors path="userVo.pass"/>  <br/>
	<input type="submit" value="전송"/>
</form>

<h2>textReqValJsp303</h2>
<form action="${cp}/textReqJsr303">
	<input type="text" name = "userId" value="brown"/>   <form:errors path="userVo.userId"/>  <br/>
	<input type="password" name = "pass" value="1234"/>  <form:errors path="userVo.pass"/>  <br/>
	<input type="submit" value="전송"/>
</form>

<h2><a href="${cp}/throwArith">ArithmeticException 강제 발생</a></h2>

<h2><a href="${cp}/thorwNofileException">thorwNofileException 강제 발생</a></h2>

</body>
</html>