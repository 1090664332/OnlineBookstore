package com.guohaoyu.controller;


import com.google.code.kaptcha.Constants;
import com.guohaoyu.pojo.User;
import com.guohaoyu.service.impl.UserServiceImpl;
import com.guohaoyu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = "/userServlet")
public class UserServlet extends BaseServlet {
    UserServiceImpl service=new UserServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.mapCopyToUserBean(new User(), request.getParameterMap());
        User userBack = service.logIn(user);
        if (userBack!=null) {
            request.getSession().setAttribute("username", userBack.getUsername());
            request.getSession().setAttribute("userId", userBack.getId());
            if (userBack != null) {

                request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "用户名或密码错误！");
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            }
        }else {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }


    protected void userNameExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        if (service.userExist(username)!=null){
            response.getWriter().write("用户名已存在!");
        }

    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user = WebUtils.mapCopyToUserBean(new User(), request.getParameterMap());
            //获取验证码内容
            String code =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            String userCode = request.getParameter("code");
            request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(code!=null&&code.equals(userCode)){
                    Boolean aBoolean = service.registerUser(user);
                    if (aBoolean){
                        request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
                    }else{
                        request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
                    }
            }else{
                request.setAttribute("registmsg","验证码错误！");
                HashMap<String, Object> map = new HashMap<>();
                map.put("name",user.getUsername());
                map.put("password",user.getPassword());
                map.put("email",user.getEmail());
                map.put("code",userCode);
                request.setAttribute("map",map);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }
        }





    }


