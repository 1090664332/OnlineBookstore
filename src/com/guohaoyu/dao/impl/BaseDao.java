package com.guohaoyu.dao.impl;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

public class BaseDao {
    /**
     * 增删改操作
     * @param coon 数据库连接
     * @param sql  要执行的sql语句
     * @param obj  obj占位符参数
     * @return  影响的行数
     */
    public int update(Connection coon,String sql,Object...obj){
        QueryRunner runner = new QueryRunner();
        int total=0;
        try {

             total=runner.update(coon,sql,obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 返回一个对象的查询
     * @param clazz 要返回对象类型
     * @param coon  数据库连接
     * @param sql   sql语句
     * @param obj   占位符的参数
     * @param <T>   泛型
     * @return 返回一个对象
     */
    public <T> T selectyOne (Class<T> clazz,Connection coon,String sql,Object...obj){
        QueryRunner runner = new QueryRunner();
        T query = null;
        try {
            query = runner.query(coon, sql, new BeanHandler<T>(clazz),obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;

    }

    /**
     * 查询多个对象
     * @param clazz
     * @param coon
     * @param sql
     * @param obj
     * @param <T>
     * @return
     */

    public <T> List<T>  selectAll(Class<T> clazz,Connection coon,String sql,Object...obj){
        QueryRunner queryRunner = new QueryRunner();
        List<T> query = null;
        try {
            query = queryRunner.query(coon, sql, new BeanListHandler<T>(clazz), obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
    /**
     * 返回一行一列的数据
     */
    public Object selectOneOne(Connection conn,String sql,Object...obj){
        QueryRunner queryRunner = new QueryRunner();
        Object query = null;
        try {
            query = queryRunner.query(conn, sql, new ScalarHandler(), obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
