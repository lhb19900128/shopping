<%@page import="com.shopping.domain.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shopping.domain.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	function submitOrder() {
		window.location.href="/shopping/order";
	}
</script>

</head>
<body>
<center>
	<%
		UserBean ub = (UserBean)session.getAttribute("userinfo");
	%>
	<h1>我的订单</h1>
	<table border="1">
	<caption>我的个人信息</caption>
	<tr>
  <td>用户名：</td>
  <td><%=ub.getName() %></td>
	</tr>
	<tr>
  <td>用户ID：</td>
  <td><%=ub.getId() %></td>
	</tr>
	<tr>
  <td>手机号码：</td>
  <td><%=ub.getTel() %></td>
	</tr>
	<tr>
  <td>电子邮件：</td>
  <td><%=ub.getEmail() %></td>
	</tr>
	<tr>
  <td>用户级别：</td>
  <td><%=ub.getGrade() %></td>
	</tr>
	</table>
	
	<br />
	<br />
	<hr />
	<br />
	<br />
	<br />
	<%
		ArrayList<BookBean> list = (ArrayList<BookBean>)request.getAttribute("orderinfo");
		float totalPrice = (Float)request.getAttribute("totalprice");
	%>
	<table border="1">
	<caption>我的商品</caption>
	<tr>
  	<th>ID</th><th>书名</th><th>价格</th><th>出版社</th><th>数量</th>
	</tr>
	<%
	for(int i = 0;i<list.size();i++){
	BookBean bb = list.get(i);
	%>
	<tr>
  	<td><%=bb.getId() %></td>
  	<td><%=bb.getName() %></td>
  	<td><%=bb.getPrice() %></td>
  	<td><%=bb.getPublishHouse() %></td>
  	<td><%=bb.getShoppingNum() %></td>
	</tr>
	<%
	}
	%>
	  <tfoot>
    <tr>
      <td colspan="5">商品总价格：${totalprice}</td>
    </tr>
  </tfoot>
	</table>
	
		<input type="button" onclick="submitOrder()" value="确认订单"/><br />

	<a href="/shopping/mycart?type=gocart">返回购物车</a>
	

</center>
</body>
</html>