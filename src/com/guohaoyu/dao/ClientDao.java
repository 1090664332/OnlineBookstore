package com.guohaoyu.dao;

import com.guohaoyu.pojo.BookBean;

import java.util.List;

public interface ClientDao {
    public List<BookBean>  queryBooks(int min,int max,int pageNum,int size);
    public Integer count(int min,int max);
}
