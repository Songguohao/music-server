package com.sou.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.sou.music.domain.Admin;
import com.sou.music.domain.ReturnMessage;
import com.sou.music.service.AdminService;
import com.sou.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/admin/login/status")
    public ReturnMessage loginStatus(@RequestBody Admin admin, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String username = admin.getUsername();
        String password = admin.getPassword();
        Boolean flag = adminService.verifyPassword(username, password);
        if (flag) {
            session.setAttribute(Consts.USERNAME, username);
            return ReturnMessage.ok("登陆成功",null);
        } else {
            return ReturnMessage.error("用户名或密码错误");
        }
    }
}
