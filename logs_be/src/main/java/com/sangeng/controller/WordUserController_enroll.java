package com.sangeng.controller;


import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.WordUser;
import com.sangeng.domain.WordUser_enroll;
import com.sangeng.service.WordUserService;
import com.sangeng.service.WordUser_enrollService;
import com.sangeng.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class WordUserController_enroll {
    @Autowired
    private WordUser_enrollService user_enrollService;


    @PostMapping("/enroll")    //注册操作    注册时不需要其他的东西
    public ResponseResult enroll(@RequestBody WordUser_enroll user_enroll){
        //检查用户名是否重复
        //若用户名存在，返回值不为空
        String EUsername = user_enrollService.enroll_username(user_enroll.getUsername());

        if(EUsername == null) {
            //说明该用户名未被占用
            //可进行下一步，校验两次密码的是否相同
        }else{
            return new ResponseResult(1,"该用户名已被占用");
        }



        //校验密码
        Boolean EUsername_pass = user_enroll.getPassword().equals(user_enroll.getRepassword());

        if(EUsername_pass) {
            //说明两次密码相同
            //可进行下一步，正式注册账号
        }else{
            return new ResponseResult(1,"两次输入的密码不一致");
        }


        //进行注册
        //获取注册好的用户名，表示是否注册成功
        user_enrollService.enroll(user_enroll);
        Map<String,Object> map;

        String PDenroll = user_enrollService.enroll_username(user_enroll.getUsername());

        if(PDenroll != null) {
            //说明注册成功
        }else{
            return new ResponseResult(1,"注册失败，请重试");
        }
        return new ResponseResult(0,"注册成功",null);
    }
}
