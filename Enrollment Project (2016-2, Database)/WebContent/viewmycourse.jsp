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
                                    <td><b>�����ڵ�</b></td>
                					<td><b>�����̸�</b></td>
                					<td><b>���ǽ�</b></td>
                					<td><b>���ǽ��۽ð�</b></td>
                					<td><b>���ǳ��ð�</b></td>
                					<td><b>����</b></td>
                					<td><b>����</b></td>
                					<td><b>�л���ȸ</b></td>
                				</tr>
                            </thead>
                            <tbody>
								<c:if test= "${cnt > 0}">
            					<c:forEach var= "i" begin= "0" end= "${cnt-1}">
	                    		<TR>
		                    		<TD>${course[i].getCid()}</TD>
		                            <TD>${course[i].getName()}</TD>
		                            <TD>${course[i].getRoom()}</TD>
		                            <TD>${course[i].getStime()}</TD>
		                            <TD>${course[i].getEtime()}</TD>
		                            <td>${course[i].getDay()}</td>
		                            <TD>${course[i].getCredit()}</TD>
		                            <form action="viewmystudent" method="POST">
		                            <td><button type="submit" name = "button${i}" value = "${i}">��ȸ</button></td>
		                   			</form>
				                </TR>
			                </c:forEach>
			            </c:if>
			            <c:if test= "${cnt == 0}">
			            <tr>
			                <td><b>���� ���°� �����ϴ�.</b></td>
			            </tr>
			            </c:if>
                            </tbody>
                        </table>
</body>
</html>