package com.sangeng.service.impl;

import com.sangeng.domain.RecordingTest;
import com.sangeng.domain.SelfTest;
import com.sangeng.domain.Word;
import com.sangeng.mapper.SelfTestMapper;
import com.sangeng.mapper.WordUser_enrollMapper;
import com.sangeng.service.SelfTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class SelfTestServiceImpl implements SelfTestService {

    @Autowired
    private SelfTestMapper selfTestMapper;

    @Override
    public String find_id(int thisUserId) {
        return selfTestMapper.find_id(thisUserId);
    }

    @Override
    public List<SelfTest> findC(HashSet<Integer> setC, String book) {
        return selfTestMapper.findC(setC,book);
    }

    @Override
    public List<Word> returnZE(HashSet<Integer> setW, String book) {
        return selfTestMapper.returnZE(setW,book);
    }

    @Override
    public List<SelfTest> findE(HashSet<Integer> setE, String book) {
        return selfTestMapper.findE(setE,book);
    }

    @Override
    public void addRecording(String userTable, RecordingTest recordingTest) {
        selfTestMapper.addRecording(userTable,recordingTest);
    }

    @Override
    public RecordingTest findRecording(String userTable, RecordingTest recordingTest) {
        return selfTestMapper.findRecording(userTable,recordingTest);
    }

    @Override
    public List<RecordingTest> findAll(String tableR) {
        return selfTestMapper.findAll(tableR);
    }
}
