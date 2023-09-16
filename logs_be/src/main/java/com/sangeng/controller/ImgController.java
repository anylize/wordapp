package com.sangeng.controller;

import com.sangeng.domain.WordUser;
import com.sangeng.service.CompanyService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.utils.ImageUtil;
import com.sangeng.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
    public ResponseResult uploadImg(@RequestBody String base64, HttpServletRequest request) throws Exception {

        //获取token，得到id
        //获取响应头的token
        String token = request.getHeader("Authorization");

        //先解析token获取用户id
        Claims thisUser = JwtUtil.parseJWT(token);

        String thisUserIdS = thisUser.getSubject();

        int thisUserId = Integer.parseInt(thisUserIdS);

        String newFileName = "logs_be/userPicS/1.jpg";

        // 判断是否base64是否包含data:image/png;base64等前缀，如果有则去除
        if (base64.contains("data:image/png;base64")) {
            base64 = base64.substring(22);
            System.out.println("包含png"+base64);
        }
        if (base64.contains("data:image/jpeg;base64")) {
            base64 = base64.substring(23);
            System.out.println("包含jpeg"+base64);
        }
        if (base64.contains("data:application/pdf;base64")) {
            base64 = base64.substring(28);
            System.out.println("包含pdf"+base64);
        }

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(base64);
        for (int i = 0; i<bytes.length; ++i) {
            // 调整异常数据
            if (bytes[i] < 0) {
                bytes[i] +=256;
            }
        }
        OutputStream outputStream = null;
        InputStream inputStream = new ByteArrayInputStream(bytes);
        // 此处判断文件夹是否存在，不存在则创建除文件外的父级文件夹
        File file = new File(newFileName);
        if (!file.exists()) {
            System.out.println("上级目录"+file.getParentFile());
            file.getParentFile().mkdirs();
        }
        try {
            // 生成指定格式文件
            outputStream = new FileOutputStream(newFileName);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }

        return new ResponseResult(200, "保存成功", null);

    }

}