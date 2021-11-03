package com.sou.music.controller;

import com.sou.music.domain.Consumer;
import com.sou.music.domain.ReturnMessage;
import com.sou.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/add")
    public ReturnMessage addConsumer(@RequestBody Consumer consumer) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        consumer.setCreateTime(sdf.parse(sdf.format(date)));
        if(consumerService.getByUsername(consumer.getUsername()) != null) {
            return ReturnMessage.error("用户名已经存在");
        }
        Boolean flag = consumerService.insert(consumer);
        if (flag) {
            return ReturnMessage.ok("新增用户成功");
        } else {
            return ReturnMessage.error("新增用户失败");
        }
    }

    @PostMapping("/update")
    public ReturnMessage updateConsumer(@RequestBody Consumer consumer) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        consumer.setUpdateTime(sdf.parse(sdf.format(date)));
        Boolean flag = consumerService.update(consumer);
        if (flag) {
            return ReturnMessage.ok("编辑用户成功");
        } else {
            return ReturnMessage.error("编辑用户失败");
        }
    }

    @GetMapping("/delete")
    public ReturnMessage deleteConsumer(@RequestParam Integer id) {
        Boolean flag = consumerService.delete(id);
        if (flag) {
            return ReturnMessage.ok("删除用户成功");
        } else {
            return ReturnMessage.error("删除用户失败");
        }
    }

    @GetMapping("/selectByPrimaryKey")
    public ReturnMessage selectByPrimaryKey(@RequestParam Integer id) {
        Consumer consumer = consumerService.selectByPrimaryKey(id);
        return ReturnMessage.ok("查询用户成功", consumer);
    }

    @GetMapping("/allConsumer")
    public ReturnMessage allConsumer() {
        List<Consumer> consumerList = consumerService.allConsumer();
        return ReturnMessage.ok("查询用户成功", consumerList);
    }


    @PostMapping("/updateAvatar")
    public ReturnMessage updateAvatar(@RequestPart("file") MultipartFile file, @RequestParam("id") int id) {
        if (file.isEmpty()) {
            return ReturnMessage.error("图片上传失败");
        }
        // 文件名：当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "avatar";
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdir();
        }

        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储数据库里的相对文件地址
        String storeImgPath = "/img/avatar/" + fileName;
        try {
            file.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvatar(storeImgPath);
            boolean flag = consumerService.update(consumer);
            if (flag) {
                return ReturnMessage.ok("图片上传成功!");
            } else {
                return ReturnMessage.error("图片上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnMessage.error("图片上传失败");
        }
    }

    @RequestMapping("/login")
    public ReturnMessage login(@RequestBody Consumer consumer) {
        boolean flag = consumerService.verifyPassword(consumer.getUsername(), consumer.getPassword());
        if (flag) {
            return ReturnMessage.ok("登陆成功！", consumerService.getByUsername(consumer.getUsername()));
        } else {
            return ReturnMessage.error("用户名或密码错误！");
        }
    }
}
