package org.study.dubboprovider;


import org.apache.dubbo.config.annotation.DubboService;
import org.study.dubboapi.bean.User;
import org.study.dubboapi.service.UserService;


@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "cuicui" + System.currentTimeMillis());
    }
}
