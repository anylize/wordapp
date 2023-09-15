package com.sangeng.service;

import com.sangeng.domain.Word;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

public interface BookShowService {

    //通过id找用户名
    String find_id(int thisUserId);

    //展示所有单词
    List<Word> showAllP(String book);

    //展示所有私有信息
    List<Word> showAll(String thisUsername,String counts,String states);

    //展示指定id的单词
    List<Word> addIn(@Param("set") HashSet<Integer> set , String book);

    //展示指定id的私有信息
    List<Word> addIns(@Param("set") HashSet<Integer> set, String thisUsername, String counts, String states);

    //修改一个单词的state
    void returnState(String thisUsername, int id, String states, int state);

    void returnCount(String thisUsername, int id, String counts, int count);
}
