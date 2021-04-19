package com.ywc.agric.service.impl;


import com.ywc.agric.dao.UserDao;
import com.ywc.agric.pojo.User;
import com.ywc.agric.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @Author YWC
 * @Date 2021/4/8 9:40
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User findByUsername(String username) {

        return userDao.findByUsername(username);
    }

    @Override
    public List<String> findImgs() {
        return userDao.findImgs();
    }
}
