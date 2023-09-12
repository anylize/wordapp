package com.sangeng.service.impl;

import com.sangeng.domain.WordUser;
import com.sangeng.mapper.WordUserMapper;
import com.sangeng.service.WordUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordUserServiceImpl implements WordUserService {

    @Autowired
    private WordUserMapper wordUserMapper;
    @Override
    public WordUser login(WordUser user){
        WordUser loginUser = wordUserMapper.login(user);
        return loginUser;

    }

    public String login_username(String string){
        String loginString = wordUserMapper.login_username(string);
        return loginString;
    }
}
