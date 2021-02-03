package com.guohaoyu.dao;

import com.guohaoyu.pojo.User;

public interface UserDao {
    public Boolean saveUser(User user);
    public User queryUserByUsernameAndPassword(String username,String password);
    public User queryUserByUsername(String username);
}
