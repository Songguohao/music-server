package com.sou.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sou.music.domain.Song;
import com.sou.music.domain.SongVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SongDao extends BaseMapper<Song> {

    List<Song> songOfName(String name);

    List<Song> songOfSingerId(Integer singerId);

    SongVO songVOOfId(Integer id);

    List<SongVO> songVOOfSingerName(@Param("name") String name);
}
