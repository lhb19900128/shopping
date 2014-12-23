<%@page import="com.shopping.domain.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1>欢迎登陆购物大厅</h1>
	<table border="1">
		<tr><th>书名</th><th>价格</th><th>出版社</th><th>点击购买</th></tr>
		<%
			//取出request的ArrayList
			ArrayList<BookBean> list = (ArrayList<BookBean>)request.getAttribute("books");
			for(int i = 0;i<list.size();i++){%>
			
			<tr><td><%=list.get(i).getName()%></td><td><%=list.get(i).getPrice() %></td><td><%=list.get(i).getPublishHouse() %></td><td><a href="??">点击购买</a></td></tr>
		<%		
			}
		%>
		<tr><td colspan="4"><input type="button" value="查看购物车"></td></tr>
	</table>
</center>
</body>
</html>