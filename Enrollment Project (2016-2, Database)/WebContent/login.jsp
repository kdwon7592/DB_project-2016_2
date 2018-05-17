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
<form action="/register/login" method="POST">
	<select name="loginPosition" id="position">
    	<option value="1" selected>학생</option>
    	<option value="2">교수님</option>
    	<option value="3">관리자</option>
    </select>
	<input type="text" name="loginId" placeholder="ID">
	<input type="text" name="loginPw" placeholder="PW">
	<button type="submit">login</button>
</form>
</body>
</html>