//package com.sangeng.controller;
//
//import com.sangeng.domain.WordUser;
//import com.sangeng.service.CompanyService;
//import com.sangeng.domain.ResponseResult;
//import com.sangeng.utils.JwtUtil;
//import io.jsonwebtoken.Claims;
//import org.apache.ibatis.annotations.Param;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import sun.misc.BASE64Decoder;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
////上传头像
//
//@RestController
//public class ImgController {
//
//    @Autowired
//    private CompanyService companyService;
//
////    @PostMapping("/loadImg")
////    public ResponseResult uploadImg(@RequestPart("file") MultipartFile file, @Param(value = "id") Integer id) {
////        String imgPath = "E:/";//获取图片路径
////
////        String ImgName = file.getOriginalFilename();
////        String lastName = ImgName.substring(ImgName.lastIndexOf(".")); //获取图片后缀名
////        String newName = id + lastName;//修改图片名字 用户 id+之前图片后缀名(.png)
////        File filePath = new File(imgPath, newName);//整合图片路径
////
////        // 判断路径是否存在，如果不存在就创建一个
////        if (!filePath.getParentFile().exists()) {
////            filePath.getParentFile().mkdirs();
////        }
////        // 将上传的文件保存到一个目标文件当中
////        try {
////            file.transferTo(filePath);
////            if (companyService.updateImgById("http://localhost:8080/img/" + newName, id) == 1) {
////                return new ResponseResult(0, "图片已保存");
////            }
////            return new ResponseResult(1, "保存失败", null);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        return new ResponseResult(1, "保存失败", null);
////
////    }
//
//    @PostMapping("/loadImg")
//    public ResponseResult uploadImg(@RequestBody String stringImg, HttpServletRequest request) throws Exception {
//
//        //获取token，得到id
//        //获取响应头的token
//        String token = request.getHeader("Authorization");
//
//
//        //先解析token获取用户id
//        Claims thisUser = JwtUtil.parseJWT(token);
//
//        String thisUserIdS = thisUser.getSubject();
//
//        int thisUserId = Integer.parseInt(thisUserIdS);
//
//        //对字节数组字符串进行Base64解码并生成图片
//        if (stringImg == null) //图像数据为空
//            return new ResponseResult<>(300,"储存图片错误",null);
//
//        BASE64Decoder decoder = new BASE64Decoder();
//        try
//        {
//            //Base64解码
//            byte[] b = decoder.decodeBuffer(stringImg);
//            for(int i=0;i<b.length;++i)
//            {
//                if(b[i]<0)
//                {//调整异常数据
//                    b[i]+=256;
//                }
//            }
//            String newfilename = System.currentTimeMillis() + UUID.randomUUID().toString().replace(".", "").substring(0, 6) + ".jpg" ;
//            String fileLocation = "/userPic";
//            String filePath = "http://localhost:8080"
//                    + fileLocation + newfilename;
////        String head_portrait = filePath;//根路径+文件名
////        File file = new File("/www/javaweb/propaganda_system/picture/" + newfilename);
//            //生成jpeg图片
////                String imgFilePath = "/www/javaweb/propaganda_system/picture/"+newfilename;//新生成的图片
//
////                String imgFilePath = "/www/javaweb/propaganda_system/picture/"+newfilename;//新生成的图片
//
//            String imgFilePath = "D:\\restaurantRes\\"+System.currentTimeMillis() + UUID.randomUUID().toString().replace(".", "").substring(0, 6)+".jpg";//新生成的图片
//            OutputStream out = new FileOutputStream(imgFilePath);
//            out.write(b);
//            out.flush();
//            out.close();
//            return filePath;
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
//
//        return new ResponseResult(1, "保存失败", null);
//
//    }
//
//}