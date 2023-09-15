package com.sangeng.mapper;


import com.sangeng.domain.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Mapper     //扫描时有用
@Repository
public interface BookShowMapper {

    String find_id(int thisUserId);

    List<Word> showAllP(String book);

    List<Word> showAll(String thisUsername,String counts,String states);

    List<Word> addIn(@Param("set") HashSet<Integer> set , String book);

    List<Word> addIns(@Param("set") HashSet<Integer> set, String thisUsername, String counts, String states);


    void returnState(String thisUsername, int id, String states, int state);

    void returnCount(String thisUsername, int id, String counts, int count);
}
