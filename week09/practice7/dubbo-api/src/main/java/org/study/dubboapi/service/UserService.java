package org.study.dubboapi.service;


import org.study.dubboapi.bean.User;

public interface UserService {

    User findById(int id);
}
