<%@page import="com.shopping.domain.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/shopping/js/check.js"></script>
<link rel="stylesheet" type="text/css" href="css/comm.css"/>
</head>
<body>

	<%
	%>
	<a href="/shopping/gohallui">返回购物大厅</a>
	<center>
	<h1>我的购物车</h1>
	<form name="cartform" action="/shopping/mycart?type=updata" method="post" onsubmit="return checkInput()">
	<table border="1" style="border-collapse: collapse;width: 600px" align="center">
	<tr><th>ID</th><th>书名</th><th>价格</th><th>出版社</th><th>数量</th><th>删除</th></tr>
	
	<%
		ArrayList<BookBean> list = (ArrayList<BookBean>)request.getAttribute("buybooks");
		for(int i = 0;i<list.size();i++){
			BookBean bean = list.get(i);
		%>
		<tr>
		<td><%=bean.getId() %></td><input type="hidden" name="id" value="<%=bean.getId() %>" >
		<td><%=bean.getName() %></td>
		<td><%=bean.getPrice() %></td>
		<td><%=bean.getPublishHouse() %></td>
		<td><input type="text" name="booknum" id="booknum" value="<%=bean.getShoppingNum()%>" /></td>
		<td><a href="/shopping/mycart?type=del&id=<%=bean.getId() %>">删除</a></td></tr>
	<%
		}
	%>
	<tr><td colspan="6" ><input type="submit" value="update"/></td></tr>
	<tr><td colspan="6" >购物车的总价格：${totalprice}元</td></tr>
	</table>
	</form>
	<a href="/shopping/mycart?type=order">提交订单</a>
	</center>
	
	
</body>
</html>