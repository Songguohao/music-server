package com.sou.music.service;

import com.sou.music.domain.Singer;

import java.util.List;

public interface SingerService {
    /**
     * 增加
     */
    boolean insert(Singer singer);

    /**
     * 修改
     */
    boolean update(Singer singer);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询
     */
    Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     */
    List<Singer> allSinger();

    /**
     * 根据歌手的名字模糊查询列表
     */
    List<Singer> singerOfName(String name);

    /**
     * 根据歌手的名性别查询列表
     */
    List<Singer> singerOfSex(Byte sex);
}
