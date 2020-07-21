package com.wl.service;

import com.wl.mapper.UserMapper;
import com.wl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        return userMapper.selectUserByNameAndPassword(username, password);
    }
}
