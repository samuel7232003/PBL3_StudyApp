<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${user!=null}">
		<c:redirect url="/home"></c:redirect>
	</c:if>
	<c:if test="${user==null }">
		<c:redirect url="/login"></c:redirect>
	</c:if>
</body>
</html>