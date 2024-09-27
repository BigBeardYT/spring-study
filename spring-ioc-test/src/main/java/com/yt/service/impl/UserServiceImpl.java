package com.yt.service.impl;

import com.yt.service.UserService;

public class UserServiceImpl implements UserService {
    public UserServiceImpl() {
        System.out.println("UserServiceImpl Constructor");
    }

    public void init() {
        System.out.println("init...");
    }

    @Override
    public void save() {
        System.out.println("插入一条用户记录>>>");
    }
}
