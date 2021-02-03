package com.guohaoyu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtils {
    static DruidDataSource druidDataSource;
    static {
        try {
            InputStream stream = jdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(stream);
            druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getCoonection(){
        DruidPooledConnection conn = null;
        try {
            conn = druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection coon){
        try {
            coon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
