package com.guohaoyu.dao.impl;

import com.guohaoyu.dao.OrderDao;
import com.guohaoyu.pojo.Order;
import com.guohaoyu.utils.jdbcUtils;

import java.sql.Connection;

public class OrderDaoImpl implements OrderDao {
    BaseDao dao=new BaseDao();

    @Override
    public Boolean saveOrder(Order order,Connection conn) {

        String sql="insert into t_order(order_id,craete_time,price,status,user_id) values(?,?,?,?,?) ";
        int update = dao.update(conn, sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
        if (update>0){
            return true;
        }else {
            return false;
        }
    }
}
