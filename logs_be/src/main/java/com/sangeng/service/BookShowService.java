package com.sangeng.service;

import com.sangeng.domain.Word;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

public interface BookShowService {
    String find_id(int thisUserId);

    List<Word> showAllP(String book);

    List<Word> showAll(String thisUsername,String counts,String states);

    List<Word> addIn(@Param("set") HashSet<Integer> set , String book);

    List<Word> addIns(@Param("set") HashSet<Integer> set, String thisUsername, String counts, String states);
}
