package com.guohaoyu.dao;

import com.guohaoyu.pojo.BookBean;

import java.util.List;

public interface BookDao {

    /**
     * 保存图书
     * @param book
     * @return
     */
    public int saveBook(BookBean book);

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

    /**
     * 更新图书
     * @param book
     * @return
     */
    public int updateBook(BookBean book);

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    public BookBean queryBookById(Integer id);

    /**
     * 查询全部图书
     * @return
     */
    public List<BookBean> queryBooks(int pageNum,int size);

    /**
     * 查询数据的总个数
     * @return
     */
    public Integer count();

}
