package com.guohaoyu.controller;

import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/kaptcha.jpg")
public class MyKaptchaServlet extends KaptchaServlet {
}
