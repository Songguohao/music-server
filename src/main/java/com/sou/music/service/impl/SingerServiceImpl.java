package com.sou.music.service.impl;

import com.sou.music.dao.SingerDao;
import com.sou.music.domain.Singer;
import com.sou.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerDao singerDao;

    @Override
    public boolean insert(Singer singer) {
        return singerDao.insert(singer) > 0;
    }

    @Override
    public boolean update(Singer singer) {
        return singerDao.update(singer) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return singerDao.delete(id) > 0;
    }

    @Override
    public Singer selectByPrimaryKey(Integer id) {
        return singerDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Singer> allSinger() {
        return singerDao.allSinger();
    }

    @Override
    public List<Singer> singerOfName(String name) {
        return singerDao.singerOfName(name);
    }

    @Override
    public List<Singer> singerOfSex(Byte sex) {
        return singerDao.singerOfSex(sex);
    }
}
