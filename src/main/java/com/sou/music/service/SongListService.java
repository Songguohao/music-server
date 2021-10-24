package com.sou.music.service;
import com.sou.music.domain.SongList;
import java.util.List;

public interface SongListService {

    /**
     * 增加
     */
    boolean insert(SongList songList);

    /**
     * 修改
     */
    boolean update(SongList songList);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询
     */
    SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     */
    List<SongList> allSongList();

    /**
     * 根据歌单的名字精确查询列表
     */
    List<SongList> songListOfTitle(String title);

    /**
     * 根据歌单的名字精确查询列表
     */
    List<SongList> songListLikeTitle(String title);

    /**
     * 根据歌单风格模糊查询
     */
    List<SongList> songListLikeStyle(String style);
}
