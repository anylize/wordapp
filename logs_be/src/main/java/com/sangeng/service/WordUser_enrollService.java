package com.sangeng.service;

import com.sangeng.domain.WordUser;
import com.sangeng.domain.WordUser_enroll;

public interface WordUser_enrollService {

    public String enroll_username(String string);

    public void enroll(WordUser_enroll user_enroll);
}
