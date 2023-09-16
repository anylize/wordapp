package com.sangeng.service;

import com.sangeng.domain.RecordingTest;
import com.sangeng.domain.SelfTest;
import com.sangeng.domain.Word;

import java.util.HashSet;
import java.util.List;

public interface SelfTestService {

    //通过id查找username
    String find_id(int thisUserId);

    //获取指定id的单词并放入SelfTest
    List<SelfTest> findC(HashSet<Integer> setC, String book);

    //通过id返回单词
    List<Word> returnZE(HashSet<Integer> setW, String book);

    List<SelfTest> findE(HashSet<Integer> setE, String book);

    void addRecording(String userTable, RecordingTest recordingTest);

    RecordingTest findRecording(String userTable, RecordingTest recordingTest);

    List<RecordingTest> findAll(String userTable);
}
