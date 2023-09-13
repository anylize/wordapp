package com.sangeng.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper     //扫描时有用
@Repository
public interface ReviseUserMapper {
    void setEmail(int id, String email);

    String getmail(int id);

    void revisceNickname(int thisUserId, String nickname);

    String getNickname(int thisUserId);

    String getPassword(int thisUserId);

    void revisePassword(int thisUserId, String password);

}
