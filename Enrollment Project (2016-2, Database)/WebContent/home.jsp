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
	<ul>
		<li>
			${userInfo.getId()}
		</li>
    	<li>
        	<a href="home.jsp">홈화면</a>
        </li>
        <li>
            <a href="login.jsp">로그인</a>
        </li>
        <li>
            <a href="join.jsp">회원가입</a>
        </li>                    
        <li>
        	<a href="admin.jsp">관리자페이지</a>
        </li>                    
        <li>
        	<a href="enroll.jsp">수강</a>
        </li>
        <li>
        	<a href="viewmycourse">교수페이지</a>
        <li>
    </ul>
    
</body>
</html>