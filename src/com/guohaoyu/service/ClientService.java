package com.guohaoyu.service;

import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.pojo.Page;

public interface ClientService {
    /**
     * 查询筛选价格后的全部的图书
     * @return
     */
    public Page<BookBean> queryBooks(int min, int max, int pageNum, int size);
}
