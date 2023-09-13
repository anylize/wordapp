package com.sangeng.service;

import com.sangeng.domain.WordUser;
import com.sangeng.domain.WordUser_enroll;

public interface WordUser_enrollService {

    //获取用户的username并返回
    public String enroll_username(String string);

    //在user表中创建用户信息
    public void enroll(WordUser_enroll user_enroll);

    //用username返回id
    int enroll_id(String string);

    //在userbook表中添加用户信息
    void enroll_userbook(int pdSenroll);

    //用user表中id来查找userbook中id以来验证已经成功建立信息
    int enroll_ids(int pdSenroll);
}
