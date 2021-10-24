package com.sou.music.controller;

import com.sou.music.domain.ReturnMessage;
import com.sou.music.domain.Song;
import com.sou.music.domain.SongList;
import com.sou.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @PostMapping("/add")
    public ReturnMessage addSongList(@RequestBody SongList songList) {
        Boolean flag = songListService.insert(songList);
        if (flag) {
            return ReturnMessage.ok("新增歌单成功");
        } else {
            return ReturnMessage.error("新增歌单失败");
        }
    }

    @PostMapping("/update")
    public ReturnMessage updateSongList(@RequestBody SongList songList) {
        Boolean flag = songListService.update(songList);
        if (flag) {
            return ReturnMessage.ok("编辑歌单成功");
        } else {
            return ReturnMessage.error("编辑歌单失败");
        }
    }

    @GetMapping("/delete")
    public ReturnMessage deleteSongList(@RequestParam Integer id) {
        Boolean flag = songListService.delete(id);
        if (flag) {
            return ReturnMessage.ok("删除歌单成功");
        } else {
            return ReturnMessage.error("删除歌单失败");
        }
    }

    @GetMapping("/selectByPrimaryKey")
    public ReturnMessage selectByPrimaryKey(@RequestParam Integer id) {
        SongList songList = songListService.selectByPrimaryKey(id);
        return ReturnMessage.ok("查询歌单成功", songList);
    }

    @GetMapping("/allSongList")
    public ReturnMessage allSongList() {
        List<SongList> songLists = songListService.allSongList();
        return ReturnMessage.ok("查询歌单成功", songLists);
    }

    @GetMapping("/songListOfTitle")
    public ReturnMessage songListOfTitle(@RequestParam String title) {
        List<SongList> songLists = songListService.songListOfTitle(title);
        return ReturnMessage.ok("查询歌单成功", songLists);
    }

    @GetMapping("/songListLikeTitle")
    public ReturnMessage songListLikeTitle(@RequestParam String title) {
        List<SongList> songLists = songListService.songListLikeTitle("%" + title + "%");
        return ReturnMessage.ok("查询歌单成功", songLists);
    }

    @GetMapping("/songListLikeStyle")
    public ReturnMessage songListLikeStyle(@RequestParam String style) {
        List<SongList> songLists = songListService.songListLikeStyle("%" + style + "%");
        return ReturnMessage.ok("查询歌单成功", songLists);
    }

    @PostMapping("/updateSongListPic")
    public ReturnMessage updateSongListPic(@RequestPart("file") MultipartFile file, @RequestParam("id") int id) {
        if (file.isEmpty()) {
            return ReturnMessage.error("图片上传失败");
        }
        // 文件名：当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "songListPic";
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdir();
        }

        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储数据库里的相对文件地址
        String storeImgPath = "/img/songListPic/" + fileName;
        try {
            file.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setImg(storeImgPath);
            boolean flag = songListService.update(songList);
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
}
