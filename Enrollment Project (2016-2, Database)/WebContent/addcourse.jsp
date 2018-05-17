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
	<form action="/register/addcourse" method="POST">
	<input type="text" name="addcid" placeholder="CID">
	<input type="text" name="addcname" placeholder="강좌이름">
	<input type="text" name="addroom" placeholder="강의실">
	<input type="text" name="addstime" placeholder="시작시간">
	<input type="text" name="addetime" placeholder="끝나는시간">
	<input type="text" name="addpid" placeholder="pid">
	<input type="text" name="addcredit" placeholder="학점">
	<select name="loginDay" id="day">
    	<option value="월요일" selected>월요일</option>
    	<option value="화요일">화요일</option>
    	<option value="수요일">수요일</option>
    	<option value="목요일">목요일</option>
    	<option value="금요일">금요일</option>
    </select>
	<button type="submit">추가</button>
</form>
</body>
</html>