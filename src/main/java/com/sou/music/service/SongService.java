package com.sou.music.service;

import com.sou.music.domain.Song;
import com.sou.music.domain.SongVO;

import java.util.List;

public interface SongService {

    /**
     * 增加
     */
    boolean insert(Song song);

    /**
     * 修改
     */
    boolean update(Song song);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询
     */
    Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     */
    List<Song> allSong();

    /**
     * 根据歌曲的名字模糊查询列表
     */
    List<Song> songOfName(String name);

    /**
     * 根据歌手模糊查询列表
     */
    List<Song> songOfSingerId(Integer singerId);

    SongVO songVOOfId(Integer id);

    List<SongVO> songVOOfSingerName(String name);
}
