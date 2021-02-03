package com.guohaoyu.service;

import com.guohaoyu.pojo.User;

public interface UseService {
    /**
     *注册用户
     */
    public Boolean registerUser(User user);
    /**
     * 用户名是否存在
     */
    public User userExist(String username);
    /**
     * 登录操作
     */
    public User logIn(User user);

}
