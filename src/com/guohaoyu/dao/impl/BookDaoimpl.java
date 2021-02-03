package com.guohaoyu.dao.impl;

import com.guohaoyu.dao.BookDao;
import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.utils.WebUtils;
import com.guohaoyu.utils.jdbcUtils;
import java.sql.Connection;
import java.util.List;

public class BookDaoimpl implements BookDao {
    @Override
    public int saveBook(BookBean book) {
        String sql="insert into t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)  values(?,?,?,?,?,?,?)";
        Connection conn = jdbcUtils.getCoonection();
        int update = new BaseDao().update(conn, sql, book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path());
        jdbcUtils.close(conn);
        return update;
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        Connection conn = jdbcUtils.getCoonection();
        int update = new BaseDao().update(conn, sql, id);
        jdbcUtils.close(conn);
        return update;
    }

    @Override
    public int updateBook(BookBean book) {
        String sql="update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where `id`=?";
        Connection conn = jdbcUtils.getCoonection();
        int update = new BaseDao().update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path(),book.getId());
        jdbcUtils.close(conn);
        return update;
    }

    @Override
    public BookBean queryBookById(Integer id) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book where id=? ";
        Connection conn = jdbcUtils.getCoonection();
        BookBean bean = new BaseDao().selectyOne(BookBean.class, conn, sql, id);
        jdbcUtils.close(conn);
        return bean;
    }

    @Override
    public List<BookBean> queryBooks(int pageNum,int size) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book limit ?,?";
        Connection conn = jdbcUtils.getCoonection();
        List<BookBean> beans = new BaseDao().selectAll(BookBean.class, conn, sql,(pageNum-1)*size,size);
        jdbcUtils.close(conn);
        return beans;
    }

    @Override
    public Integer count() {
        String sql="select count(id) from t_book";
        Connection conn = jdbcUtils.getCoonection();
        Integer total = WebUtils.IntegerParseInt(new BaseDao().selectOneOne(conn, sql).toString(),0);
        jdbcUtils.close(conn);
        return total;
    }
}
