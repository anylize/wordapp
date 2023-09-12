package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.WordUser;
import com.sangeng.service.WordUserService;
import com.sangeng.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//用于登录检测

@RestController
public class WordUserController {

    @Autowired
    private WordUserService userService;

    @PostMapping("/login")    //登录操作
    public ResponseResult login(@RequestBody WordUser user){
        //校验用户名
        String Rusername = userService.login_username(user.getUsername());
        if(Rusername != null) {
            //账号存在,直接过
        }else{
            return new ResponseResult(1,"该用户名不存在");
        }

        //校验密码
        WordUser loginUser = userService.login(user);
        Map<String,Object> map;
        if(loginUser != null) {
            //代表校验成功，返回token
            map = new HashMap<>();
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(),String.valueOf(loginUser.getId()),null);
            map.put("token",token);
        }else{
            return new ResponseResult(1,"密码错误");
        }
        return new ResponseResult(0,"登陆成功",map);
    }
}
