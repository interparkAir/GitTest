<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Book List.</h1>
	<table border="1">
		<tr align="center">
			<td width="100px">${title}이디</td>
			<td width="100px">이름</td>
			<td width="100px">작가</td>
			<td width="100px">가격</td>
			<td width="100px">장르</td>
			<td width="100px">출판사</td>
			<td width="100px">수량</td>
		</tr>
		#foreach($air in $airInfo)
			<tr align="center">
				<td width="100px">$!air.PR_PNR1</td>
				<td width="100px">$!air.PR_CU_ID</td>
				<td width="100px">$!air.PR_PNR_LOC1</td>
				<td width="100px">$!air.PR_CU_NAME</td>
				
			</tr>
		#end
		
	</table>
</body>
</html>
