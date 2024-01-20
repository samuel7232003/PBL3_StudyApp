<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/PBL3/upload-avatar" method="post"
		enctype="multipart/form-data">
		<input type="file" name="avatar" value="Upload" id="avatar"
			accept="image/*" /> <input type="submit" value="Submit"
			name="usubmit">
	</form>
	<form action="/PBL3/edit-profile" method="post">
		<br>
		<br>
		<table>
			<tr>
				<td>Update:</td>
				<td width='10px'></td>
				<td>First Name: <input type="text" name="firstName">
				</td>
				<td>Last Name<input type="text" name="lastName">
				</td>
				<td>Date: <input type="date" name="dateOfBirth">
				</td>
				<td>School: <select name="school_id">
						<c:forEach items="${listSchool}" var="item">
							<option value="${item.id}">${item.nameSchool}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" name="usubmit"></td>
			</tr>
		</table>
	</form>
</body>
</html>