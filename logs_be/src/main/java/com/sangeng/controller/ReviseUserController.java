package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.WordUser;
import com.sangeng.domain.WordUser_enroll;
import com.sangeng.service.ReviseUserService;
import com.sangeng.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revise")
public class ReviseUserController {

    @Autowired
    private ReviseUserService reviseUserService;

    @SneakyThrows
    @PostMapping("/email")
    public ResponseResult revisceEmail(@RequestBody WordUser user){

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(user.getToken());

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

//        user.setUsername(thisUser.getSubject());

        //通过id修改email
        reviseUserService.setEmail(thisUserId,user.getEmail());

        //通过id返回email
        String Gemail = reviseUserService.getmail(thisUserId);

        //通过对比，如果email相同，则说明修改成功
        if(Gemail.equals(user.getEmail())){
            //修改成功，直接过
        }else{
            return new ResponseResult(300,"修改失败",null);
        }

        return new ResponseResult(200,"修改邮件成功",null);
    }

    @SneakyThrows
    @PostMapping("/nickname")
    public ResponseResult revisceNickname(@RequestBody WordUser user){

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(user.getToken());

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

//        user.setUsername(thisUser.getSubject());

        //通过id修改nickname
        reviseUserService.revisceNickname(thisUserId,user.getNickname());

        //通过id返回email
        String Gnickname = reviseUserService.getNickname(thisUserId);

        //通过对比，如果email相同，则说明修改成功
        if(Gnickname.equals(user.getNickname())){
            //修改成功，直接过
        }else{
            return new ResponseResult(300,"修改失败",null);
        }

        return new ResponseResult(200,"修改昵称成功",null);
    }


    @SneakyThrows
    @PostMapping("/password")
    public ResponseResult reviscePassword(@RequestBody WordUser_enroll user_enroll){

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


}
