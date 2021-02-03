package com.guohaoyu.service.impl;

import com.guohaoyu.dao.impl.ClientDaoImpl;
import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.pojo.Page;
import com.guohaoyu.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public Page<BookBean> queryBooks(int min,int max,int pageNum, int size) {
        ClientDaoImpl dao = new ClientDaoImpl();
        //总记录数
        Integer count = dao.count(min, max);
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
        List<BookBean> bookItems = dao.queryBooks(min,max,page.getPageNum(), size);
        page.setPageSize(size);
        page.setPageCount(count);
        page.setItems(bookItems);
        return page;
    }
}
