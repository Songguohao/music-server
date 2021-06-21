package com.sou.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.sou.music.domain.ReturnMessage;
import com.sou.music.domain.Singer;
import com.sou.music.service.SingerService;
import com.sou.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @RequestMapping("/add")
    public ReturnMessage addSinger(@RequestBody Singer singer) {
        Boolean flag = singerService.insert(singer);
        if (flag) {
            return ReturnMessage.ok("新增歌手成功", null);
        } else {
            return ReturnMessage.error("新增歌手失败");
        }
    }

    @RequestMapping("/update")
    public ReturnMessage updateSinger(@RequestBody Singer singer) {
        Boolean flag = singerService.update(singer);
        if (flag) {
            return ReturnMessage.ok("编辑歌手成功", null);
        } else {
            return ReturnMessage.error("编辑歌手失败");
        }
    }

    @RequestMapping("/delete")
    public ReturnMessage deleteSinger(@RequestParam Integer id) {
        Boolean flag = singerService.delete(id);
        if (flag) {
            return ReturnMessage.ok("编辑歌手成功", null);
        } else {
            return ReturnMessage.error("编辑歌手失败");
        }
    }

    @RequestMapping("/selectByPrimaryKey")
    public ReturnMessage selectByPrimaryKey(@RequestParam Integer id) {
        Singer singer = singerService.selectByPrimaryKey(id);
        return ReturnMessage.ok("查询歌手成功", singer);
    }

    @RequestMapping("/allSinger")
    public ReturnMessage allSinger() {
        List<Singer> singerList = singerService.allSinger();
        return ReturnMessage.ok("查询歌手成功", singerList);
    }

    @RequestMapping("/singerOfName")
    public ReturnMessage singerOfName(String name) {
        List<Singer> singerList = singerService.singerOfName(name);
        return ReturnMessage.ok("查询歌手成功", singerList);
    }

    @RequestMapping("/singerOfSex")
    public ReturnMessage singerOfSex(Byte sex) {
        List<Singer> singerList = singerService.singerOfSex(sex);
        return ReturnMessage.ok("查询歌手成功", singerList);
    }
}
