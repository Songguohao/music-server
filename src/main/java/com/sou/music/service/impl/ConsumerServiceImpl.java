package com.sou.music.service.impl;

import com.sou.music.dao.ConsumerDao;
import com.sou.music.domain.Consumer;
import com.sou.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDao consumerDao;

    @Override
    public Boolean insert(Consumer consumer) {
        return consumerDao.insert(consumer) > 0;
    }

    @Override
    public Boolean update(Consumer consumer) {
        return consumerDao.updateById(consumer) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return consumerDao.deleteById(id) > 0;
    }

    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerDao.selectById(id);
    }

    @Override
    public List<Consumer> allConsumer() {
        return consumerDao.selectList(null);
    }

    @Override
    public Boolean verifyPassword(String username, String password) {
        return consumerDao.verifyPassword(username, password) > 0;
    }

    @Override
    public Consumer getByUsername(String username) {
        return consumerDao.getByUsername(username);
    }
}
