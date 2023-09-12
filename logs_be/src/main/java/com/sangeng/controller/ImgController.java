package com.sangeng.controller;

import com.sangeng.service.CompanyService;
import com.sangeng.domain.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.File;
import java.io.IOException;
 
@RestController
@RequestMapping("company")
public class ImgController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/loadImg")
    public ResponseResult uploadImg(@RequestPart("file") MultipartFile file, @Param(value = "id") Integer id) {
        String imgPath = "C:/web/img/";//获取图片路径

        String ImgName = file.getOriginalFilename();
        String lastName = ImgName.substring(ImgName.lastIndexOf(".")); //获取图片后缀名
        String newName = id + lastName;//修改图片名字 用户 id+之前图片后缀名(.png)
        File filePath = new File(imgPath, newName);//整合图片路径

        // 判断路径是否存在，如果不存在就创建一个
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        // 将上传的文件保存到一个目标文件当中
        try {
            file.transferTo(filePath);
            if (companyService.updateImgById("http://localhost:8989/img/" + newName, id) == 1) {
                return new ResponseResult(0, "图片已保存");
            }
            return new ResponseResult(1, "保存失败", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseResult(1, "保存失败", null);

    }
}