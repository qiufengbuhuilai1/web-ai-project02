package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException{
//        log.info("接收参数：{}，{}，{}", name, age, file);
//        //保存文件
//        file.transferTo(new File("D:/images/"+file.getOriginalFilename()));
//
//        return Result.success();
//    }
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception{
        log.info("接收参数：{}",file.getOriginalFilename());
        //保存文件到阿里云oss

        String url=aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());

        log.info("文件上传成功，返回url：{}",url);



        return Result.success(url);
    }
}
