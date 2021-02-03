package com.guohaoyu.controller;

import com.guohaoyu.pojo.Cart;
import com.guohaoyu.service.impl.OrderServiceImpl;
import com.guohaoyu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/orderServlet")
public class OrderServlet extends BaseServlet {

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = WebUtils.IntegerParseInt(req.getParameter("userId"),-1) ;
        Cart cart = (Cart) req.getSession().getAttribute("cartObj");
        String orderId = new OrderServiceImpl().saveOredrService(userId, cart);
        req.getSession().setAttribute("orderId",orderId);
        cart.clear();
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }
}
