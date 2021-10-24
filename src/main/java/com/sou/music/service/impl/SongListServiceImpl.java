package com.sou.music.service.impl;

import com.sou.music.dao.SongListDao;
import com.sou.music.domain.SongList;
import com.sou.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListDao songListDao;

    @Override
    public boolean insert(SongList songList) {
        return songListDao.insert(songList) > 0;
    }

    @Override
    public boolean update(SongList songList) {
        return songListDao.updateById(songList) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return songListDao.deleteById(id) > 0;
    }

    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListDao.selectById(id);
    }

    @Override
    public List<SongList> allSongList() {
        return songListDao.selectList(null);
    }

    @Override
    public List<SongList> songListOfTitle(String title) {
        return songListDao.songListOfTitle(title);
    }

    @Override
    public List<SongList> songListLikeTitle(String title) {
        return songListDao.songListLikeTitle(title);
    }

    @Override
    public List<SongList> songListLikeStyle(String style) {
        return songListDao.songListLikeStyle(style);
    }
}
