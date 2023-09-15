package com.sangeng.mapper;


import com.sangeng.domain.SelfTest;
import com.sangeng.domain.Word;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Mapper     //扫描时有用
@Repository
public interface SelfTestMapper {

    List<Word> returnZE(HashSet<Integer> setW, String book);

    List<SelfTest> findC(HashSet<Integer> setC, String book);

    String find_id(int thisUserId);


    List<SelfTest> findE(HashSet<Integer> setE, String book);
}
