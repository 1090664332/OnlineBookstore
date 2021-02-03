package com.guohaoyu.dao;

import com.guohaoyu.pojo.OrderItem;

import java.sql.Connection;

public interface OrderItemDao {
    public void saveOrderItem(OrderItem orderItem, Connection conn);
}
