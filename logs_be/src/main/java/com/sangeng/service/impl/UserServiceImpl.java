package com.sangeng.service.impl;

import com.sangeng.domain.User;
import com.sangeng.mapper.UserMapper;
import com.sangeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll(){
        return userMapper.findAll();
    }
}
