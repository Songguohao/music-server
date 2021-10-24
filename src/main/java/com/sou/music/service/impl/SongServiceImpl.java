package com.sou.music.service.impl;

import com.sou.music.dao.SongDao;
import com.sou.music.domain.Song;
import com.sou.music.domain.SongVO;
import com.sou.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongDao songDao;

    @Override
    public boolean insert(Song song) {
        return songDao.insert(song) > 0;
    }

    @Override
    public boolean update(Song song) {
        return songDao.updateById(song) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return songDao.deleteById(id) > 0;
    }

    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songDao.selectById(id);
    }

    @Override
    public List<Song> allSong() {
        return songDao.selectList(null);
    }

    @Override
    public List<Song> songOfName(String name) {
        return songDao.songOfName(name);
    }

    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songDao.songOfSingerId(singerId);
    }

    @Override
    public SongVO songVOOfId(Integer id) {
        return songDao.songVOOfId(id);
    }

    @Override
    public List<SongVO> songVOOfSingerName(String name) {
        return songDao.songVOOfSingerName("%" + name + "%");
    }


}
