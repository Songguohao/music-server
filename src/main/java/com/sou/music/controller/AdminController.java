package com.sou.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.sou.music.domain.Admin;
import com.sou.music.service.AdminService;
import com.sou.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/admin/login/status")
    public Object loginStatus(@RequestBody Admin admin, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String username = admin.getUsername();
        String password = admin.getPassword();
        Boolean flag = adminService.verifyPassword(username, password);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put("msg", "登陆成功");
            session.setAttribute(Consts.USERNAME, username);
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put("msg", "用户名或密码错误");
        }
        return jsonObject;
    }
}
