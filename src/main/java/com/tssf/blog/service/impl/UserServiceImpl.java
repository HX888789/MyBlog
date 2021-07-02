package com.tssf.blog.service.impl;

import com.tssf.blog.dao.UserRepository;
import com.tssf.blog.domain.User;
import com.tssf.blog.service.UserService;
import com.tssf.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Util.getMD5(password));
        return user;
    }
}
