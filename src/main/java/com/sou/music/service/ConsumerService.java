package com.sou.music.service;

import com.sou.music.domain.Consumer;

import java.util.List;

public interface ConsumerService {

    Boolean insert(Consumer consumer);

    Boolean update(Consumer consumer);

    Boolean delete(Integer id);

    Consumer selectByPrimaryKey(Integer id);

    List<Consumer> allConsumer();

    Boolean verifyPassword(String username, String password);

    Consumer getByUsername(String username);
}
