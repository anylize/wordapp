package com.sangeng.mapper;

import com.sangeng.domain.WordUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper     //扫描时有用
@Repository
public interface WordUserMapper {
    WordUser login(WordUser user);

    String login_username(String string);
}
