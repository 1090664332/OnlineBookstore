package com.guohaoyu.dao.impl;

import com.guohaoyu.dao.OrderItemDao;
import com.guohaoyu.pojo.OrderItem;
import com.guohaoyu.utils.jdbcUtils;

import java.sql.Connection;

public class OrderItemDaoImpl implements OrderItemDao {

    @Override
    public void saveOrderItem(OrderItem orderItem,Connection conn) {

        String sql="insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        new BaseDao().update(conn,sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());

    }
}
