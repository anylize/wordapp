package com.sangeng.mapper;


import com.sangeng.domain.WordUser;
import com.sangeng.domain.WordUser_enroll;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper     //扫描时有用
@Repository
public interface WordUser_enrollMapper {

    void enroll(WordUser_enroll user_enroll);

    String enroll_username(String string);
}
