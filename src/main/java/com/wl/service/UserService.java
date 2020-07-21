package com.wl.service;

import com.wl.pojo.User;

public interface UserService {

    User checkUser(String username, String password);

}
