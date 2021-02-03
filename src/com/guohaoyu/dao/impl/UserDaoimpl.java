package com.guohaoyu.dao.impl;

import com.guohaoyu.dao.UserDao;
import com.guohaoyu.pojo.User;
import com.guohaoyu.utils.jdbcUtils;

import java.sql.Connection;

public class UserDaoimpl implements UserDao {

    @Override
    public Boolean saveUser(User user) {
        Connection coon = jdbcUtils.getCoonection();
        String sql="insert into t_user(username,password,email) values(?,?,?)";
        int update = new BaseDao().update(coon, sql, user.getUsername(), user.getPassword(), user.getEmail());
        jdbcUtils.close(coon);
        if(update>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        Connection coon = jdbcUtils.getCoonection();
        String sql="select `id`,`username`,`password` from t_user where  username=?and password=? ";
        User user = new BaseDao().selectyOne(User.class, coon, sql, username, password);
        jdbcUtils.close(coon);
        return user;
    }

    @Override
    public User queryUserByUsername(String username) {
        Connection coon = jdbcUtils.getCoonection();
        String sql="select username from t_user where username=?";
        User user = new BaseDao().selectyOne(User.class, coon, sql, username);
        jdbcUtils.close(coon);
        return user;
    }
}
