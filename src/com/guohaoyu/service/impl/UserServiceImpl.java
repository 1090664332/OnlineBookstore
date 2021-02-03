package com.guohaoyu.service.impl;

import com.guohaoyu.dao.impl.UserDaoimpl;
import com.guohaoyu.pojo.User;
import com.guohaoyu.service.UseService;

public class UserServiceImpl implements UseService {
    @Override
    public Boolean registerUser(User user) {
        Boolean boolean01 = new UserDaoimpl().saveUser(user);
        return boolean01;
    }

    @Override
    public User userExist(String username) {
        User user = new UserDaoimpl().queryUserByUsername(username);
        return user;
    }

    @Override
    public User logIn(User user) {
        User user1 = new UserDaoimpl().queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return user1;
    }
}
