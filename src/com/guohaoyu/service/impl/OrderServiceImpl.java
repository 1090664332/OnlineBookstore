package com.guohaoyu.service.impl;

import com.guohaoyu.dao.impl.BookDaoimpl;
import com.guohaoyu.dao.impl.OrderDaoImpl;
import com.guohaoyu.dao.impl.OrderItemDaoImpl;
import com.guohaoyu.pojo.*;
import com.guohaoyu.service.OrderService;
import com.guohaoyu.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    OrderDaoImpl dao=new OrderDaoImpl();
    OrderItemDaoImpl itemDao=new OrderItemDaoImpl();
    BookDaoimpl bookDaoimpl=new BookDaoimpl();
    @Override
    public String saveOredrService(Integer id, Cart cart) {
        Connection conn = jdbcUtils.getCoonection();
        String orderId= null;
        try {
            conn.setAutoCommit(false);
            long millis = System.currentTimeMillis();
            orderId = millis+""+id;
            Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,id);
            dao.saveOrder(order,conn);
//            int a=10/0;
            Map<Integer, CartItem> map = cart.getItem();
            for (CartItem item:map.values()){
                OrderItem orderItem = new OrderItem(item.getId(), item.getName(), item.getCount(), item.getPrice(), item.getTotalPrice(), orderId);
                itemDao.saveOrderItem(orderItem,conn);
                BookBean book = bookDaoimpl.queryBookById(item.getId());
                book.setSales(book.getSales()+item.getCount());
                book.setStock(book.getStock()-item.getCount());
                bookDaoimpl.updateBook(book);
                conn.commit();
            }
        } catch (SQLException e) {

            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            jdbcUtils.close(conn);
        }
        return orderId;
    }
}
