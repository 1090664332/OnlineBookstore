package com.guohaoyu.dao.impl;

import com.guohaoyu.dao.ClientDao;
import com.guohaoyu.pojo.BookBean;
import com.guohaoyu.utils.WebUtils;
import com.guohaoyu.utils.jdbcUtils;

import java.sql.Connection;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    @Override
    public List<BookBean> queryBooks(int min,int max,int pageNum,int size) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book where price between ? and ? order by price limit ?,?";
        Connection conn = jdbcUtils.getCoonection();
        List<BookBean> beans = new BaseDao().selectAll(BookBean.class, conn, sql,min,max,(pageNum-1)*size,size);
        jdbcUtils.close(conn);
        return beans;
    }

    @Override
    public Integer count(int min,int max) {
        String sql="select count(id) from t_book where price between ? and ?";
        Connection coon = jdbcUtils.getCoonection();
        Integer total = WebUtils.IntegerParseInt(new BaseDao().selectOneOne(coon, sql, min, max).toString(), 0);
        jdbcUtils.close(coon);
        return total;
    }
}
