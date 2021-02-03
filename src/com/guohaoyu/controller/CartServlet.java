package com.guohaoyu.controller;

import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.pojo.Cart;
import com.guohaoyu.pojo.CartItem;
import com.guohaoyu.service.impl.BookServiceImpl;
import com.guohaoyu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/cartServlet")
public class CartServlet extends BaseServlet {
    BookServiceImpl bookServiceImpl = new BookServiceImpl();


    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = WebUtils.IntegerParseInt(req.getParameter("id"), -1);
        BookBean bookBean = bookServiceImpl.queryBookById(id);
        Cart cartObj=(Cart)req.getSession().getAttribute("cartObj");
        if (cartObj ==null){
            cartObj=new Cart();
           req.getSession().setAttribute("cartObj",cartObj);
        }
        CartItem cartItem = new CartItem(id,bookBean.getName(),1,bookBean.getPrice(),bookBean.getPrice());
        cartObj.addItem(cartItem);
        req.getSession().setAttribute("lastName",bookBean.getName());
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void delItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = WebUtils.IntegerParseInt(req.getParameter("id"), -1);
        Cart cartObj =(Cart) req.getSession().getAttribute("cartObj");
        cartObj.delItem(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cartObj=(Cart)req.getSession().getAttribute("cartObj");
        cartObj.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
