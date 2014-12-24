package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.service.CartService;


//�����û�������Ʒ������
@WebServlet(name="CartServlet",urlPatterns="/mycart")
public class CartServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String type = req.getParameter("type");
		//��Ҫ��ʾ���鼮����req��
		CartService myCart = (CartService) req.getSession().getAttribute("mycart");

		if(type.equals("add")){
			//�����û��빺�����Ʒid
			String id = req.getParameter("id");
			myCart.addBook(id);
			
			req.setAttribute("buybooks", (Object)myCart.getAllBook());
			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
			//��ת����ʾ�ҵĹ��ﳵ
			req.getRequestDispatcher("gohallui").forward(req, resp);
		}else if(type.equals("del")){
			//�����û���ɾ������Ʒid
			String id = req.getParameter("id");
			myCart.delBook(id);
			
			req.setAttribute("buybooks", (Object)myCart.getAllBook());
			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
			//��ת����ʾ�ҵĹ��ﳵ
			req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
		}else if(type.equals("updata")){
			//�û����µ���ź�����
			String[] ids = req.getParameterValues("id");
			String[] nums = req.getParameterValues("booknum"); 
			myCart.updataBook(ids, nums);
			
			req.setAttribute("buybooks", (Object)myCart.getAllBook());
			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
			//��ת����ʾ�ҵĹ��ﳵ
			req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
		}else if(type.equals("pay")){
			//�����û��빺�����Ʒid
			String id = req.getParameter("id");
			myCart.addBook(id);
			
//			req.setAttribute("buybooks", (Object)myCart.getAllBook());
//			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
//			//��ת����ʾ�ҵĹ��ﳵ
//			req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
			resp.sendRedirect("refreshservlet");
		}else if(type.equals("gocart")){
			req.setAttribute("buybooks", (Object)myCart.getAllBook());
			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
			//��ת����ʾ�ҵĹ��ﳵ
			req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
		}else if(type.equals("order")){
			
//			//�û����µ���ź�����
//			String[] ids = req.getParameterValues("id");
//			String[] nums = req.getParameterValues("booknum"); 
//			myCart.updataBook(ids, nums);
			
			float totalPrice = myCart.calTotalPrice();
			req.setAttribute("orderinfo",myCart.getAllBook());
			req.setAttribute("totalprice", totalPrice);
			req.getRequestDispatcher("/WEB-INF/myorder.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	
}
