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
 						<table class="table">
                            <thead class="thead-inverse">
                                <tr>
                                    <td><b>학번</b></td>
                					<td><b>학생이름</b></td>
                					<td><b>전공</b></td>
                				</tr>
                            </thead>
                            <tbody>
								<c:if test= "${cnt > 0}">
            					<c:forEach var= "i" begin= "0" end= "${cnt-1}">
	                    		<TR>
		                    		<TD>${userinfo[i].getId()}</TD>
		                            <TD>${userinfo[i].getName()}</TD>
		                            <TD>${userinfo[i].getMajor()}</TD>
		                        </TR>
			                </c:forEach>
			            </c:if>
			            <c:if test= "${cnt == 0}">
			            <tr>
			                <td><b>듣는 학생이 없습니다.</b></td>
			            </tr>
			            </c:if>
                            </tbody>
                        </table>
</body>
</html>