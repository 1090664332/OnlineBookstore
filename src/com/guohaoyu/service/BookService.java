package com.guohaoyu.service;

import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.pojo.Page;

import java.util.List;

public interface BookService {
    /**
     * 添加图书
     * @param book
     */
    public void addBook(BookBean book);

    /**
     * 更新图书
     * @param book
     */
    public void updateBook(BookBean book);

    /**
     * 根据id删除图书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    public BookBean queryBookById(Integer id);

    /**
     * 查询全部的图书
     * @return
     */
    public List<BookBean> queryBooks(int pageNum,int size);

    /**
     * 查询数据的总个数
     * @return
     */
    public Integer countTotal();
    /**
     * 生成Page对象
     */
    public Page<BookBean> page(int pageNum,int size);
}
