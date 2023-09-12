package com.sangeng.mapper;

import com.sangeng.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper     //扫描时有用
@Repository
public interface UserMapper {
    List<User> findAll();

}
