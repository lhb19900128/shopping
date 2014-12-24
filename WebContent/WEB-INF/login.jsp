<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<center>
	<h1>登陆界面</h1>
	<form action="/shopping/logincheck" method="post">
		<table border="1">
			<tr>
			<td>用户ID:</td>
			<td><input type="text" name="id"/></td>
			</tr>
			<tr>
			<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
			<td><input type="password" name="passwd" /></td>
			</tr>
			<tr>
			<td><input type="submit" value="登陆"/></td>
			<td align="center"><input type="reset" value="清空"/></td>
			</tr>
		</table>
	</form>
</center>
</body>
</html>