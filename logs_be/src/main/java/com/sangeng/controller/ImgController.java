package com.sangeng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sangeng.service.CompanyService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

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
    public ResponseResult uploadImg(@RequestBody String img, HttpServletRequest request) throws Exception {

        //获取token，得到id
        //获取响应头的token
        String token = request.getHeader("Authorization");

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(token);

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);


        String base64Pic = "";
        String picName = "";
        JSONObject jsonObject = (JSONObject) JSON.parse(img);

        base64Pic = jsonObject.getString("avatar");
        picName = jsonObject.getString("picName");

        if (base64Pic == null) {

            // 图像数据为空

            return new ResponseResult<>(301,"结果为空",null);

        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            String baseValue = base64Pic.replaceAll(" ", "+");//前台在用Ajax传base64值的时候会把base64中的+换成空格，所以需要替换回来。

            byte[] b = decoder.decodeBuffer(baseValue.replace("data:image/jpeg;base64,", ""));//去除base64中无用的部分

            base64Pic = base64Pic.replace("base64,", "");

            System.out.println(b);



            String imgFilePath = "E:\\"+ thisUserId +".jpg";

            try {
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {// 调整异常数据
                        b[i] += 256;
                    }
                }
                // 生成jpeg图片
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();

                companyService.updateImgById(imgFilePath,thisUserId);

                return new ResponseResult<>(200,"上传成功",null);

            } catch (Exception e) {
                return new ResponseResult<>(300,"结果为空",null);
            }

        }

    }

}