package com.guohaoyu.controller;

import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.pojo.Page;
import com.guohaoyu.service.impl.ClientServiceImpl;
import com.guohaoyu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/clientServlet")
public class ClientServlet extends BaseServlet {
    ClientServiceImpl csi=new ClientServiceImpl();

    protected void screeningOfPrice (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer beginpage = WebUtils.IntegerParseInt(req.getParameter("pageNum"),1);
        Integer Intsize = WebUtils.IntegerParseInt(req.getParameter("size"), Page.getPage_Size());
        Integer min = WebUtils.IntegerParseInt(req.getParameter("min"), 0);
        Integer value=Integer.MAX_VALUE;
        Integer max = WebUtils.IntegerParseInt(req.getParameter("max"), value);
        Page<BookBean> page = csi.queryBooks(min, max, beginpage, Intsize);
        if (min == 0) {
            req.setAttribute("min", null);
        } else {
            req.setAttribute("min", min);
        }
        if ((max == value)) {
            req.setAttribute("max", null);
        } else {
            req.setAttribute("max", max);
        }
        req.setAttribute("page",page);

        req.getRequestDispatcher("/client/clientIndex.jsp").forward(req,resp);

    }

    protected void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/OnlineBookstore/pages/user/login.jsp");
    }


    protected void getCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
