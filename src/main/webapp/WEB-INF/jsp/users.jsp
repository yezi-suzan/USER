<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>id</th>
			<th>用户名</th>
			<th>密码</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.password}</td>
				<%-- 				<td><a href="delete?id=${user.id}">delete</a></td> --%>
			</tr>
		</c:forEach>
	</table>
	<form action="delete" method="post">
		<input type="text" name="id"> 
		<input type="submit"
			value="确认删除">
	</form>

	<a href="index">返回首页</a>
</body>
</html>