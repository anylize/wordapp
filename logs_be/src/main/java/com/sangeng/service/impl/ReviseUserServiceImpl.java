package com.sangeng.service.impl;

import com.sangeng.domain.WordUser;
import com.sangeng.mapper.ReviseUserMapper;
import com.sangeng.service.ReviseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviseUserServiceImpl implements ReviseUserService {

    @Autowired
    private ReviseUserMapper reviseUserMapper;

    @Override
    public void setEmail(int id, String email) {
        reviseUserMapper.setEmail(id,email);
    }

    @Override
    public String getmail(int id) {
        String ThisEmail = reviseUserMapper.getmail(id);
        return ThisEmail;
    }

    @Override
    public void revisceNickname(int thisUserId, String nickname) {
        reviseUserMapper.revisceNickname(thisUserId,nickname);
    }

    @Override
    public String getNickname(int thisUserId) {
        return reviseUserMapper.getNickname(thisUserId);
    }

    @Override
    public String getPassword(int thisUserId) {
        return reviseUserMapper.getPassword(thisUserId);
    }

    @Override
    public void reviscePassword(int thisUserId, String password) {
        reviseUserMapper.revisePassword(thisUserId,password);
    }

    @Override
    public WordUser useId(int thisUserId) {
        return reviseUserMapper.useId(thisUserId);
    }

}
