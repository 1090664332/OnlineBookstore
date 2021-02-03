package com.guohaoyu.service.impl;

import com.guohaoyu.dao.impl.BookDaoimpl;
import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.pojo.Page;
import com.guohaoyu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public void addBook(BookBean book) {
        int i = new BookDaoimpl().saveBook(book);
    }

    @Override
    public void updateBook(BookBean book) {
        int i = new BookDaoimpl().updateBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        int i = new BookDaoimpl().deleteBookById(id);
    }

    @Override
    public BookBean queryBookById(Integer id) {
        BookBean bean = new BookDaoimpl().queryBookById(id);
        return bean;
    }

    @Override
    public List<BookBean> queryBooks(int pageNum,int size) {
        List<BookBean> beans = new BookDaoimpl().queryBooks(pageNum,size);
        return beans;
    }

    @Override
    public Page<BookBean> page(int pageNum,int size){
        BookDaoimpl daoimpl = new BookDaoimpl();
        //总记录数
        Integer count = daoimpl.count();
        //总页数
        Integer pageTotal;
        if(count%size==0){
            pageTotal=count/size;
        }else{
            pageTotal=count/size+1;
        }
        Page<BookBean> page = new Page();
        page.setPageTotal(pageTotal);
        page.setPageNum(pageNum);
        //获取页面数据
        List<BookBean> bookItems = daoimpl.queryBooks(page.getPageNum(), size);
        page.setPageSize(size);
        page.setPageCount(count);
        page.setItems(bookItems);
        return page;
    }

    @Override
    public Integer countTotal() {
        Integer count = new BookDaoimpl().count();
        return count;
    }
}
