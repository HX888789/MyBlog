package com.tssf.blog.service;

import com.tssf.blog.domain.User;

public interface UserService {
    User checkUser(String username,String password);
}
