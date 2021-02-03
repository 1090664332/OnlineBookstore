package com.guohaoyu.controller;

import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.pojo.Page;
import com.guohaoyu.service.impl.BookServiceImpl;
import com.guohaoyu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/manager/bookServlet")
public class BookServlet extends BaseServlet {
    BookServiceImpl bookService=new BookServiceImpl();
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNum = WebUtils.IntegerParseInt(request.getParameter("pageNum"), Integer.MAX_VALUE);
        BookBean bookBean = WebUtils.mapCopyToUserBean(new BookBean(), request.getParameterMap());
        bookService.addBook(bookBean);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=selectAllBook&pageNum="+pageNum);

    }
    protected void delBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNum = WebUtils.IntegerParseInt(request.getParameter("pageNum"), 1);
        Integer integer = WebUtils.IntegerParseInt(request.getParameter("id"),-1);
        bookService.deleteBookById(integer);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=selectAllBook&pageNum="+pageNum);

    }
    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNum = WebUtils.IntegerParseInt(request.getParameter("pageNum"), 1);
        BookBean bookBean = WebUtils.mapCopyToUserBean(new BookBean(), request.getParameterMap());
        bookService.updateBook(bookBean);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=selectAllBook&pageNum="+pageNum);

    }
    protected void selectAllBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNum = request.getParameter("pageNum");
        Integer beginpage = WebUtils.IntegerParseInt(pageNum,1);
        String size = request.getParameter("size");
        Integer Intsize = WebUtils.IntegerParseInt(size, Page.getPage_Size());
        Page<BookBean> page = new BookServiceImpl().page(beginpage, Intsize);
        request.setAttribute("page",page);
        request.setAttribute("pageType","selectAllBook");
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void FormEcho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer pageNum = WebUtils.IntegerParseInt(request.getParameter("pageNum"), 1);
        BookBean bookBean = bookService.queryBookById(WebUtils.IntegerParseInt(id,-1));
        request.setAttribute("book",bookBean);
        request.setAttribute("pageNum",pageNum);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp?pageNum"+pageNum).forward(request,response);
    }

    protected void firstPageData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNum = request.getParameter("pageNum");
        Integer beginpage = WebUtils.IntegerParseInt(pageNum,1);
        String size = request.getParameter("size");
        Integer Intsize = WebUtils.IntegerParseInt(size, Page.getPage_Size());
        Page<BookBean> page = new BookServiceImpl().page(beginpage, Intsize);
        request.setAttribute("page",page);
        request.setAttribute("pageType","firstPageData");
        request.getRequestDispatcher("/client/clientIndex.jsp").forward(request,response);
    }


}
