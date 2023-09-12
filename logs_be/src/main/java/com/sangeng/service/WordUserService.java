package com.sangeng.service;

import com.sangeng.domain.WordUser;

public interface WordUserService {

    //检查密码是否正确
    public WordUser login(WordUser user);

    //检查用户名是否存在
    public String login_username(String string);
}
