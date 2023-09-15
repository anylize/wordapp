package com.sangeng.service;

import com.sangeng.domain.WordUser;

public interface ReviseUserService {

    //通过id查询用户并改变email
    void setEmail(int id, String email);


    //通过id返回email
    String getmail(int id);

    //通过id修改昵称
    void revisceNickname(int thisUserId, String nickname);

    //通过id得到昵称
    String getNickname(int thisUserId);

    //通过id获取密码（在修改密码时可用来表示获取旧密码）
    String getPassword(int thisUserId);

    //通过id修改密码
    void reviscePassword(int thisUserId, String password);

    WordUser useId(int thisUserId);
}
