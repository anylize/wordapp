package com.sangeng.service.impl;


import com.sangeng.domain.Word;
import com.sangeng.mapper.BookShowMapper;
import com.sangeng.service.BookShowService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookShowServiceImpl implements BookShowService {

    @Autowired
    private BookShowMapper bookShowMapper;


    @Override
    public String find_id(int thisUserId) {
        return bookShowMapper.find_id(thisUserId);
    }

    @Override
    public List<Word> showAllP(String book) {
        return bookShowMapper.showAllP(book);
    }

    @Override
    public List<Word> showAll(String thisUsername,String counts,String states) {
        return bookShowMapper.showAll(thisUsername,counts,states);
    }

    @Override
    public List<Word> addIn(@Param("set") HashSet<Integer> set , String book) {
        return bookShowMapper.addIn(set,book);
    }

    @Override
    public List<Word> addIns(@Param("set") HashSet<Integer> set, String thisUsername, String counts, String states) {
        return bookShowMapper.addIns(set,thisUsername,counts,states);
    }

    @Override
    public void returnState(String thisUsername, int id, String states, int state) {
        bookShowMapper.returnState(thisUsername,id,states,state);
    }

    @Override
    public void returnCount(String thisUsername, int id, String counts, int count) {
        bookShowMapper.returnCount(thisUsername,id,counts,count);
    }

}
