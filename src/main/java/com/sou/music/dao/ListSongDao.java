package com.sou.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sou.music.domain.ListSong;

import java.util.List;

public interface ListSongDao extends BaseMapper<ListSong> {

    List<ListSong> listSongOfSongList(Integer songListId);
}
