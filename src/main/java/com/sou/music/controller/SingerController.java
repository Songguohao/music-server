package com.sou.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.sou.music.domain.ReturnMessage;
import com.sou.music.domain.Singer;
import com.sou.music.service.SingerService;
import com.sou.music.utils.Consts;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @PostMapping("/add")
    public ReturnMessage addSinger(@RequestBody Singer singer) {
        Boolean flag = singerService.insert(singer);
        if (flag) {
            return ReturnMessage.ok("新增歌手成功", null);
        } else {
            return ReturnMessage.error("新增歌手失败");
        }
    }

    @PostMapping("/update")
    public ReturnMessage updateSinger(@RequestBody Singer singer) {
        Boolean flag = singerService.update(singer);
        if (flag) {
            return ReturnMessage.ok("编辑歌手成功", null);
        } else {
            return ReturnMessage.error("编辑歌手失败");
        }
    }

    @DeleteMapping("/delete")
    public ReturnMessage deleteSinger(@RequestParam Integer id) {
        Boolean flag = singerService.delete(id);
        if (flag) {
            return ReturnMessage.ok("编辑歌手成功", null);
        } else {
            return ReturnMessage.error("编辑歌手失败");
        }
    }

    @GetMapping("/selectByPrimaryKey")
    public ReturnMessage selectByPrimaryKey(@RequestParam Integer id) {
        Singer singer = singerService.selectByPrimaryKey(id);
        return ReturnMessage.ok("查询歌手成功", singer);
    }

    @GetMapping("/allSinger")
    public ReturnMessage allSinger() {
        List<Singer> singerList = singerService.allSinger();
        return ReturnMessage.ok("查询歌手成功", singerList);
    }

    @GetMapping("/singerOfName")
    public ReturnMessage singerOfName(String name) {
        List<Singer> singerList = singerService.singerOfName(name);
        return ReturnMessage.ok("查询歌手成功", singerList);
    }

    @GetMapping("/singerOfSex")
    public ReturnMessage singerOfSex(Byte sex) {
        List<Singer> singerList = singerService.singerOfSex(sex);
        return ReturnMessage.ok("查询歌手成功", singerList);
    }

    @PostMapping("/updateSingerPic")
    public ReturnMessage updateSingerPic(@RequestPart("file")MultipartFile file, @RequestParam("id")int id) {
        if(file.isEmpty()) {
            return ReturnMessage.error("图片上传失败");
        }
        // 文件名：当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "singerPic";
        File newFile = new File(filePath);
        if(!newFile.exists()){
            newFile.mkdir();
        }

        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储数据库里的相对文件地址
        String storeImgPath = "/img/singerPic/" + fileName;
        try {
            file.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setImg(storeImgPath);
            boolean flag = singerService.update(singer);
            if(flag) {
                return ReturnMessage.ok("图片上传成功!");
            } else {
                return ReturnMessage.error("图片上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnMessage.error("图片上传失败");
        }

    }
}
