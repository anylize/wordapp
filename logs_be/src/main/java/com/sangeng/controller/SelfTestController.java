package com.sangeng.controller;

import com.sangeng.domain.*;
import com.sangeng.service.BookShowService;
import com.sangeng.service.SelfTestService;
import com.sangeng.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/test")
public class SelfTestController {

    @Autowired
    private SelfTestService selfTestService;


    @PostMapping("/question")
    public ResponseResult selfTest(@RequestBody Book books, HttpServletRequest request) throws Exception {

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
        String thisUsername = selfTestService.find_id(thisUserId);


        //随机生成0到99的数字
        int maxNum = 99; // 最大随机数为99
        int setSize = books.getNumber(); //每组随机数的数量为number
        //中转英和英转中的数量
        int setSizeC = (int) (setSize * books.getRate());
        //英转中的数量
        int setSizeE = setSize - setSizeC;

        //setC存储随机中转英文单词的索引
        HashSet<Integer> setC = new HashSet<Integer>();
        Random randomC = new Random();

        while (setC.size() < setSizeC) {
            int num = randomC.nextInt(maxNum) + 1;
            setC.add(num);
        }

        //setC存储随机英转中文单词的索引
        HashSet<Integer> setE = new HashSet<Integer>();
        Random randomE = new Random();

        while (setE.size() < setSizeE) {
            int num = randomE.nextInt(maxNum) + 1;
            setE.add(num);
        }

        //用与保存随机到中文转英语的单词
        //返回中转英的question和answer
        List<SelfTest> answersC = selfTestService.findC(setC, books.getBook());

        //填充中转英返回的答案
        for (int i = 0; i < setSizeC; i++) {

            //随机生成单词的错误选项
            HashSet<Integer> setW = new HashSet<Integer>();
            Random randomW = new Random();

            while (setW.size() < 3) {
                int numZ = randomW.nextInt(maxNum) + 1;
                setW.add(numZ);
            }

            //获取这几个单词的翻译和单词
            List<Word> returnZ = selfTestService.returnZE(setW, books.getBook());

            //先用字符串收集最后的选项
            List<String> strings = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                strings.add(returnZ.get(j).getWord());
            }
            strings.add(answersC.get(i).getTrueAnswer());

            //将选项添加到答案中
            answersC.get(i).setAnswer(strings);

        }

        //用与保存随机到英文转中文的单词
        //返回英转中的question和answer
        List<SelfTest> answersE = selfTestService.findE(setE, books.getBook());

        //填充英转中返回的答案
        for (int i = 0; i < setSizeE; i++) {

            //随机生成单词的错误选项
            HashSet<Integer> setWs = new HashSet<Integer>();
            Random randomW = new Random();

            while (setWs.size() < 3) {
                int numZ = randomW.nextInt(maxNum) + 1;
                setWs.add(numZ);
            }

            //获取这几个单词的翻译和单词
            List<Word> returnE = selfTestService.returnZE(setWs, books.getBook());

            //先用字符串收集最后的选项
            List<String> stringsS = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                stringsS.add(returnE.get(j).getMeaning());
            }
            stringsS.add(answersE.get(i).getTrueAnswer());

            //将选项添加到答案中
            answersE.get(i).setAnswer(stringsS);
        }

        answersC.addAll(answersE);


        if (answersC != null) {
            //获取成功直接过
        } else {
            return new ResponseResult(300, "单词自测启动失败", null);
        }

        return new ResponseResult(200, "单词自测开始成功", answersC);
    }


    @PostMapping("/recordingTest")
    public ResponseResult recordingTest(@RequestBody RecordingTest recordingTest, HttpServletRequest request) throws Exception {

        //获取响应头的token
        String token = request.getHeader("Authorization");

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(token);

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

        //通过id获取用户名
        String thisUsername = selfTestService.find_id(thisUserId);

        //拼装用户表名
        String userTable = thisUsername + "t";

        //添加考试记录
        selfTestService.addRecording(userTable, recordingTest);

        //返回记录，如果存在说明成功
        RecordingTest recordingTest1 = selfTestService.findRecording(userTable,recordingTest);

        if (recordingTest1 == null ) {
            //获取成功直接过
        } else {
            return new ResponseResult(300, "考试记录添加失败", null);
        }

        return new ResponseResult(200, "考试记录添加成功",null);

    }
}