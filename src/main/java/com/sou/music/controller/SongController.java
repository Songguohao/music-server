package com.sou.music.controller;

import com.sou.music.domain.ReturnMessage;
import com.sou.music.domain.Singer;
import com.sou.music.domain.Song;
import com.sou.music.domain.SongVO;
import com.sou.music.service.SingerService;
import com.sou.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping(value = "/add")
    public ReturnMessage addSong(Song song, @RequestPart("file") MultipartFile file) {

        String img = "/img/songPic/tubiao.jpg"; // 默认图片
        if (file.isEmpty()) {
            return ReturnMessage.error("歌曲上传失败");
        }
        // 文件名：当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "song";
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdir();
        }

        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储数据库里的相对文件地址
        String storePath = "/song/" + fileName;
        try {
            file.transferTo(dest);
            // 格式化时间
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            song.setCreateTime(sdf.parse(sdf.format(date)));
            song.setImg(img);
            song.setUrl(storePath);
            boolean flag = songService.insert(song);
            if (flag) {
                return ReturnMessage.ok("歌曲添加成功");
            } else {
                return ReturnMessage.error("歌曲添加失败");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return ReturnMessage.error("歌曲添加失败");
        }
    }

    @GetMapping("/allSong")
    public ReturnMessage allSong() {
        List<Song> songList = songService.allSong();
        return ReturnMessage.ok("查询歌手成功", songList);
    }

    @GetMapping(value = "/singer/detail")
    public ReturnMessage songOfSingerId(@RequestParam("id") Integer singerId) {
        List<Song> songList = songService.songOfSingerId(singerId);
        return ReturnMessage.ok("查询歌曲成功", songList);
    }

    @GetMapping(value = "/detail")
    public ReturnMessage songOfSongId(@RequestParam Integer songId) {
        Song song = songService.selectByPrimaryKey(songId);
        return ReturnMessage.ok("查询歌曲成功", song);
    }

    @GetMapping(value = "detailVO")
    public ReturnMessage songVOOfSongId(@RequestParam Integer songId) {
        SongVO songVO = songService.songVOOfId(songId);
        return ReturnMessage.ok("查询歌曲成功", songVO);
    }

    @GetMapping(value = "/songName/detail")
    public ReturnMessage songOfSongName(@RequestParam String songName) {
        List<Song> songList = songService.songOfName(songName);
        Song song = new Song();
        if (songList.isEmpty()) {
            song = null;
        } else {
            song = songList.get(0);
        }
        return ReturnMessage.ok("查询歌曲成功", song);
    }

    @GetMapping(value = "/singerName/detail")
    public ReturnMessage songLikeSingerName(@RequestParam String singerName) {
        List<SongVO> songVOList = songService.songVOOfSingerName(singerName);
        return ReturnMessage.ok("查询歌曲成功", songVOList);
    }


    @PostMapping("/update")
    public ReturnMessage updateSong(@RequestBody Song song) {
        Boolean flag = songService.update(song);
        if (flag) {
            return ReturnMessage.ok("编辑歌曲成功");
        } else {
            return ReturnMessage.error("编辑歌曲失败");
        }
    }

    @GetMapping("/delete")
    public ReturnMessage deleteSong(@RequestParam Integer id) {
        // 先删除本地文件
        Song song = songService.selectByPrimaryKey(id);
        if (song != null && song.getUrl() != null) {
            File file = new File(System.getProperty("user.dir") + song.getUrl());
            if (file.exists()) {
                boolean deleteFile = file.delete();
                if (!deleteFile) {
                    return ReturnMessage.error("删除歌曲失败");
                }
            }
        }

        Boolean flag = songService.delete(id);
        if (flag) {
            return ReturnMessage.ok("删除歌曲成功");
        } else {
            return ReturnMessage.error("删除歌曲失败");
        }
    }

    @PostMapping("/updateSongPic")
    public ReturnMessage updateSongPic(@RequestPart("file") MultipartFile file, @RequestParam("id") int id) {
        if (file.isEmpty()) {
            return ReturnMessage.error("图片上传失败");
        }
        // 文件名：当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "songPic";
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdir();
        }

        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储数据库里的相对文件地址
        String storeImgPath = "/img/songPic/" + fileName;
        try {
            file.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setImg(storeImgPath);
            boolean flag = songService.update(song);
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

    @PostMapping("/updateSongUrl")
    public ReturnMessage updateSongUrl(@RequestPart("file") MultipartFile file, @RequestParam("id") int id) {
        if (file.isEmpty()) {
            return ReturnMessage.error("歌曲上传失败");
        }

        // 先删除本地文件
        Song song = songService.selectByPrimaryKey(id);
        if (song != null && song.getUrl() != null) {
            File oldFile = new File(System.getProperty("user.dir") + song.getUrl());
            if (oldFile.exists()) {
                boolean deleteFile = oldFile.delete();
                if (!deleteFile) {
                    return ReturnMessage.error("歌曲上传失败");
                }
            }
        }

        // 文件名：当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "song";
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdir();
        }

        // 实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储数据库里的相对文件地址
        String storePath = "/song/" + fileName;
        try {
            file.transferTo(dest);
            song.setId(id);
            song.setUrl(storePath);
            boolean flag = songService.update(song);
            if (flag) {
                return ReturnMessage.ok("歌曲上传成功!");
            } else {
                return ReturnMessage.error("歌曲上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnMessage.error("歌曲上传失败");
        }
    }
}
