package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.WordUser;
import com.sangeng.domain.WordUser_enroll;
import com.sangeng.service.ReviseUserService;
import com.sangeng.service.WordUser_enrollService;
import com.sangeng.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.io.*;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;

import static org.apache.commons.io.IOUtils.toByteArray;

@RestController
@RequestMapping("/revise")
public class ReviseUserController {

    @Autowired
    private ReviseUserService reviseUserService;


    @GetMapping("/get")
    public ResponseResult retuenUser(@RequestHeader("Authorization") String authorization) throws Exception {

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(authorization);

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

        WordUser wordUser = reviseUserService.useId(thisUserId);

        String base = wordUser.getUserPic();

        if (base == null) {
            return null;
        }
        try {
            byte[] b = Files.readAllBytes(Paths.get(base));
            base = Base64.getEncoder().encodeToString(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        wordUser.setUserPic(base);

        if(wordUser != null){
            //修改成功，直接过
        }else{
            return new ResponseResult(300,"获取信息失败",null);
        }

        return new ResponseResult(200,"获取信息成功",wordUser);
    }



    @PostMapping("/emailNickname")
    public ResponseResult revisceEnickname(@RequestBody WordUser user, HttpServletRequest request) throws Exception {

        //获取响应头的token
        String token = request.getHeader("Authorization");

        user.setToken(token);

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(user.getToken());

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

//        user.setUsername(thisUser.getSubject());

        //通过id修改email
        reviseUserService.setEmail(thisUserId,user.getEmail());

        //通过id返回email
        String Gemail = reviseUserService.getmail(thisUserId);

        //通过id修改nickname
        reviseUserService.revisceNickname(thisUserId,user.getNickname());

        //通过id返回nickname
        String Gnickname = reviseUserService.getNickname(thisUserId);

        //通过对比，如果nickname相同，则说明修改成功
        //通过对比，如果email相同，则说明修改成功
        if(Gemail.equals(user.getEmail()) && Gnickname.equals(user.getNickname())){
            //修改成功，直接过
        }else{
            return new ResponseResult(300,"修改失败",null);
        }

        return new ResponseResult(200,"修改邮件和昵称成功",null);
    }




    @SneakyThrows
    @PostMapping("/password")
    public ResponseResult reviscePassword(@RequestBody WordUser_enroll user_enroll,HttpServletRequest request){

        //获取响应头的token
        String token = request.getHeader("Authorization");

        user_enroll.setToken(token);

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(user_enroll.getToken());

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

//        user.setUsername(thisUser.getSubject());

        //通过id拿到原密码
        String Gpassword = reviseUserService.getPassword(thisUserId);

        //对比原密码是否正确
        Boolean EUsername_pass = Gpassword.equals(user_enroll.getPasswordY());

        if(EUsername_pass) {
            //说明原密码正确
            //进行俩次新密码对比
        }else{
            return new ResponseResult(300,"原始密码错误");
        }

        //新密码对比
        Boolean TUsername_pass = user_enroll.getPassword().equals(user_enroll.getRepassword());

        if(TUsername_pass) {
            //说明两次密码相同
            //可以更改密码
        }else{
            return new ResponseResult(300,"两次输入的新密码不一致");
        }

        //可以改密码了
        reviseUserService.reviscePassword(thisUserId,user_enroll.getPassword());

        //通过id对比新密码，看看是否修改成功
        String GpasswordS = reviseUserService.getPassword(thisUserId);


        //通过对比，如果密码相同，则说明修改成功
        if(GpasswordS.equals(user_enroll.getRepassword())){
            //修改成功，直接过
        }else{
            return new ResponseResult(300,"修改失败",null);
        }

        return new ResponseResult(200,"修改密码成功",null);
    }


    @SneakyThrows
    @PostMapping("/pic")
    public ResponseResult reviscePic(@RequestBody String stringPic,HttpServletRequest request){

        //获取响应头的token
        String token = request.getHeader("Authorization");

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(token);

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);




        return new ResponseResult(200,"储存成功",null);
    }



}
