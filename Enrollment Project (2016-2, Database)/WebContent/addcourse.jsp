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
	<input type="text" name="addcname" placeholder="�����̸�">
	<input type="text" name="addroom" placeholder="���ǽ�">
	<input type="text" name="addstime" placeholder="���۽ð�">
	<input type="text" name="addetime" placeholder="�����½ð�">
	<input type="text" name="addpid" placeholder="pid">
	<input type="text" name="addcredit" placeholder="����">
	<select name="loginDay" id="day">
    	<option value="������" selected>������</option>
    	<option value="ȭ����">ȭ����</option>
    	<option value="������">������</option>
    	<option value="�����">�����</option>
    	<option value="�ݿ���">�ݿ���</option>
    </select>
	<button type="submit">�߰�</button>
</form>
</body>
</html>