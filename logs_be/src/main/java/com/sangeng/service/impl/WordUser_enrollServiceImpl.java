package com.sangeng.service.impl;

import com.sangeng.domain.WordUser;
import com.sangeng.domain.WordUser_enroll;
import com.sangeng.mapper.WordUserMapper;
import com.sangeng.mapper.WordUser_enrollMapper;
import com.sangeng.service.WordUser_enrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordUser_enrollServiceImpl implements WordUser_enrollService {

    @Autowired
    private WordUser_enrollMapper wordUser_enrollMapper;

    //返回注册好的用户
    @Override
    public void enroll(WordUser_enroll user_enroll){
        wordUser_enrollMapper.enroll(user_enroll);

    }

    //返回同名字符串（没有就空）
    public String enroll_username(String string){
        String enrollString = wordUser_enrollMapper.enroll_username(string);
        return enrollString;
    }
}
