package com.sou.music.controller;

import com.sou.music.domain.ListSong;
import com.sou.music.domain.ReturnMessage;
import com.sou.music.domain.Song;
import com.sou.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    @PostMapping(value = "/add")
    public ReturnMessage addListSong(@RequestBody ListSong listSong) {
        boolean flag = listSongService.insert(listSong);
        if (flag) {
            return ReturnMessage.ok("歌曲添加成功");
        } else {
            return ReturnMessage.error("歌曲添加失败");
        }
    }

    @PostMapping("/update")
    public ReturnMessage updateListSong(@RequestBody ListSong listSong) {
        Boolean flag = listSongService.update(listSong);
        if (flag) {
            return ReturnMessage.ok("编辑歌曲成功");
        } else {
            return ReturnMessage.error("编辑歌曲失败");
        }
    }

    @GetMapping("/delete")
    public ReturnMessage deleteListSong(Integer id) {
        Boolean flag = listSongService.delete(id);
        if (flag) {
            return ReturnMessage.ok("删除歌曲成功");
        } else {
            return ReturnMessage.error("删除歌曲失败");
        }
    }

    @GetMapping("/detail")
    public ReturnMessage detail(Integer songListId) {
        List<ListSong> listSongs = listSongService.listSongOfSongList(songListId);
        return ReturnMessage.ok("查询歌曲成功", listSongs);

    }
}
