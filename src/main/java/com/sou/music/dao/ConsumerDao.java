package com.sou.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sou.music.domain.Consumer;

public interface ConsumerDao extends BaseMapper<Consumer> {

    int verifyPassword(String username, String password);

    Consumer getByUsername(String username);
}
