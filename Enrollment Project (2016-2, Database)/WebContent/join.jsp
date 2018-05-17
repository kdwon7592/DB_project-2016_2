<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*"%>
    <%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="/register/join" method="POST">
	<select name="inputPosition" id="position">
    	<option value="1" selected>학생</option>
    	<option value="2">교수님</option>
    </select>
	<select name="inputMajor" id="major">
    	<option value="컴공" selected>컴공</option>
    	<option value="정통">정통</option>
    	<option value="경영">경영</option>
    </select>
	<input type="text" name="joinId" placeholder="ID">
	<input type="text" name="joinPw" placeholder="PW">
	<input type="text" name="joinName" placeholder="NAME">
	<button type="submit">join</button>
</form>
</body>
</html>