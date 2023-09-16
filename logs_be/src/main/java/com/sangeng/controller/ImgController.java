package com.sangeng.controller;

import com.sangeng.domain.WordUser;
import com.sangeng.service.CompanyService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//上传头像

@RestController
public class ImgController {

    @Autowired
    private CompanyService companyService;

//    @PostMapping("/loadImg")
//    public ResponseResult uploadImg(@RequestPart("file") MultipartFile file, @Param(value = "id") Integer id) {
//        String imgPath = "E:/";//获取图片路径
//
//        String ImgName = file.getOriginalFilename();
//        String lastName = ImgName.substring(ImgName.lastIndexOf(".")); //获取图片后缀名
//        String newName = id + lastName;//修改图片名字 用户 id+之前图片后缀名(.png)
//        File filePath = new File(imgPath, newName);//整合图片路径
//
//        // 判断路径是否存在，如果不存在就创建一个
//        if (!filePath.getParentFile().exists()) {
//            filePath.getParentFile().mkdirs();
//        }
//        // 将上传的文件保存到一个目标文件当中
//        try {
//            file.transferTo(filePath);
//            if (companyService.updateImgById("http://localhost:8080/img/" + newName, id) == 1) {
//                return new ResponseResult(0, "图片已保存");
//            }
//            return new ResponseResult(1, "保存失败", null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return new ResponseResult(1, "保存失败", null);
//
//    }

    @PostMapping("/loadImg")
    public ResponseResult uploadImg(@RequestBody String stringImg, HttpServletRequest request) throws Exception {

        //获取token，得到id
        //获取响应头的token
        String token = request.getHeader("Authorization");


        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(token);

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);


        return new ResponseResult(1, "保存失败", null);

    }

}