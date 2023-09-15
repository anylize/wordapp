package com.sangeng.controller;


import com.sangeng.domain.*;
import com.sangeng.service.BookShowService;
import com.sangeng.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/book")
public class BookShowController {

    @Autowired
    private BookShowService bookShowService;

    @PostMapping("/show")
    public ResponseResult showAll(@RequestBody Book books, HttpServletRequest request) throws Exception {


        //获取响应头的token
        String token = request.getHeader("Authorization");

        books.setToken(token);

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(books.getToken());

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

        //拼装私有的变量
        String Snum = books.getBook().substring(4);
        String counts = "count" + Snum;
        String states = "state" + Snum;

        //通过id获取用户名
        String thisUsername = bookShowService.find_id(thisUserId);

        //获取单词表共有部分（展示从0开始）
        List<Word> words = bookShowService.showAllP(books.getBook());

        //获取用户单词表私有部分
        List<Word> wordsR = bookShowService.showAll(thisUsername,counts,states);

        //然后将两部分拼接
        if(counts.equals("count1")){
            for(int i = 0 ; i < 100 ; i++){
                words.get(i).setCount(wordsR.get(i).getCount1());
                words.get(i).setState(wordsR.get(i).getState1());
            }
        } else if (counts.equals("count2")) {
            for(int i = 0 ; i < 100 ; i++){
                words.get(i).setCount(wordsR.get(i).getCount2());
                words.get(i).setState(wordsR.get(i).getState2());
            }
        }else {
            for(int i = 0 ; i < 100 ; i++){
                words.get(i).setCount(wordsR.get(i).getCount3());
                words.get(i).setState(wordsR.get(i).getState3());
            }
        }

        if(words != null) {
            //获取成功直接过
        }else {
            return new ResponseResult(300,"单词书获取失败",null);
        }

        return new ResponseResult(200,"单词书获取成功",words);
    }


    //背诵单词
    @PostMapping("/recite")
    public ResponseResult recite(@RequestBody Book books,HttpServletRequest request) throws Exception {

        //获取响应头的token
        String token = request.getHeader("Authorization");

        books.setToken(token);

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(books.getToken());

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

        //拼装私有的变量
        String Snum = books.getBook().substring(4);
        String counts = "count" + Snum;
        String states = "state" + Snum;

        //通过id获取用户名
        String thisUsername = bookShowService.find_id(thisUserId);

        //随机生成0到99的数字
        int maxNum = 99; // 最大随机数为99
        int setSize = books.getNumber(); //每组随机数的数量为number

        //set存储随机整数
        HashSet<Integer> set = new HashSet<Integer>();
        Random random = new Random();

        while (set.size() < setSize) {
            int num = random.nextInt(maxNum) + 1;
            set.add(num);
        }

        //用与保存随机到的单词
        List<Word> Pwords = bookShowService.addIn(set,books.getBook());

        //获取随机单词表私有部分
        List<Word> wordsR = bookShowService.addIns(set,thisUsername,counts,states);

        //然后将两部分拼接
        if(counts.equals("count1")){
            for(int i = 0; i < set.size() ; i++ ){

                Pwords.get(i).setCount(wordsR.get(i).getCount1());
                Pwords.get(i).setState(wordsR.get(i).getState1());
                }
        } else if (counts.equals("count2")) {
            for(int i = 0 ; i < set.size() ; i++ ){
                Pwords.get(i).setCount(wordsR.get(i).getCount2());
                Pwords.get(i).setState(wordsR.get(i).getState2());
            }
        }else {
            for(int i = 0 ; i < set.size() ; i++ ){
                Pwords.get(i).setCount(wordsR.get(i).getCount3());
                Pwords.get(i).setState(wordsR.get(i).getState3());
            }
        }



        if(Pwords != null) {
            //获取成功直接过
        }else {
            return new ResponseResult(300,"单词背诵启动失败",null);
        }

        return new ResponseResult(200,"单词背诵开始成功",Pwords);
    }


    @PostMapping("/alterState")
    public ResponseResult alterState(@RequestBody AlterWord alterWord, HttpServletRequest request) throws Exception {


        //获取响应头的token
        String token = request.getHeader("Authorization");


        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(token);

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

        //拼装私有的变量
        String Snum = alterWord.getBook().substring(4);
        String counts = "count" + Snum;
        String states = "state" + Snum;

        //通过id获取用户名
        String thisUsername = bookShowService.find_id(thisUserId);

        //在表中对比状态
        //先获取表中数据
        //获取用户单词表私有部分
        List<Word> wordsR = bookShowService.showAll(thisUsername,counts,states);
        int j = 0;


        if(wordsR.size() == alterWord.getWordList().size()){
            //表示单词获取成功，继续下一步
        }else {
            return new ResponseResult(300,"单词状态更新失败",null);
        }

        for (int i = 0 ; i < wordsR.size() ; i++){
            if(wordsR.get(i).getState() != alterWord.getWordList().get(i).getState()){
                //若状态不同，则进行更改数据库
                bookShowService.returnState(thisUsername,alterWord.getWordList().get(i).getId(),states,alterWord.getWordList().get(i).getState());
                j = i;
            }
        }

        //查找id为j的单词
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(j);
        List<Word> verify = bookShowService.addIns(set,thisUsername,counts,states);

        if(verify != null) {
            //获取成功直接过
        }else {
            return new ResponseResult(300,"单词状态更新失败",null);
        }

        return new ResponseResult(200,"单词状态更新成功",null);
    }

    @PostMapping("/alterCount")
    public ResponseResult alterCount(@RequestBody AlterWord alterWord, HttpServletRequest request) throws Exception {


        //获取响应头的token
        String token = request.getHeader("Authorization");


        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(token);

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

        //拼装私有的变量
        String Snum = alterWord.getBook().substring(4);
        String counts = "count" + Snum;
        String states = "state" + Snum;

        //通过id获取用户名
        String thisUsername = bookShowService.find_id(thisUserId);

        //在表中对比状态
        //先获取表中数据
        //获取用户单词表私有部分
        List<Word> wordsR = bookShowService.showAll(thisUsername,counts,states);
        int j = 0;


        if(wordsR.size() == alterWord.getWordList().size()){
            //表示单词获取成功，继续下一步
        }else {
            return new ResponseResult(300,"单词次数更新失败",null);
        }

        for (int i = 0 ; i < wordsR.size() ; i++){
            if(wordsR.get(i).getCount() != alterWord.getWordList().get(i).getCount()){
                //若状态不同，则进行更改数据库
                bookShowService.returnCount(thisUsername,alterWord.getWordList().get(i).getId(),counts,alterWord.getWordList().get(i).getCount());
                j = alterWord.getWordList().get(i).getId();
            }
        }

        //查找id为j的单词
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(j);
        List<Word> verify = bookShowService.addIns(set,thisUsername,counts,states);

        if(verify.get(0).getCount() == alterWord.getWordList().get(j).getCount()) {
            //获取成功直接过
        }else {
            return new ResponseResult(300,"单词次数更新失败",null);
        }

        return new ResponseResult(200,"单词次数更新成功",null);
    }

}
