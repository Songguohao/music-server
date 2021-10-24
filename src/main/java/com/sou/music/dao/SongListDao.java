package com.sou.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sou.music.domain.SongList;

import java.util.List;

public interface SongListDao extends BaseMapper<SongList> {

    List<SongList> songListOfTitle(String title);

    List<SongList> songListLikeTitle(String title);

    List<SongList> songListLikeStyle(String style);
}
