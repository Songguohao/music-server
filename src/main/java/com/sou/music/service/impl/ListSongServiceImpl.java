package com.sou.music.service.impl;


import com.sou.music.dao.ListSongDao;
import com.sou.music.domain.ListSong;
import com.sou.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongDao listSongDao;

    @Override
    public boolean insert(ListSong listSong) {
        return listSongDao.insert(listSong) > 0;
    }

    @Override
    public boolean update(ListSong listSong) {
        return listSongDao.updateById(listSong) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return listSongDao.deleteById(id) > 0;
    }

    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongDao.selectById(id);
    }

    @Override
    public List<ListSong> allListSong() {
        return listSongDao.selectList(null);
    }

    @Override
    public List<ListSong> listSongOfSongList(Integer songListId) {
        return listSongDao.listSongOfSongList(songListId);
    }
}
