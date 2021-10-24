package com.sou.music.service;

import com.sou.music.domain.ListSong;
import java.util.List;

public interface ListSongService {

    /**
     * 增加
     */
    boolean insert(ListSong listSong);

    /**
     * 修改
     */
    boolean update(ListSong listSong);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询
     */
    ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单所有歌曲
     */
    List<ListSong> allListSong();

    /**
     * 根据歌单id查询其所有歌曲
     */
    List<ListSong> listSongOfSongList(Integer songListId);
}
