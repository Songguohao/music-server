package com.sou.music.service.impl;

import com.sou.music.dao.AdminDao;
import com.sou.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Boolean verifyPassword(String username, String password) {
        Integer result = adminDao.verifyPassword(username, password);
        return result > 0;
    }
}
