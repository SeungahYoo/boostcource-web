<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	요청 처리 과정에서 에러가 발생하였습니다.
	<br>
	<br> 
<!-- 	<a href="main.jsp">메인 화면으로 돌아가기</a> -->
	<%
		System.out.println("Error Type: "+exception.getClass().getName());
		System.out.println("Error Message: "+exception.getMessage());
	%>
</body>
</html>