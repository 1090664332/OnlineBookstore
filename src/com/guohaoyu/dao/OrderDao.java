package com.guohaoyu.dao;

import com.guohaoyu.pojo.Order;

import java.sql.Connection;

public interface OrderDao {
    public Boolean saveOrder(Order order, Connection conn);
}
