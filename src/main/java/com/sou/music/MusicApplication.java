package com.sou.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    热更新，热加载：
    1.Ctrl+Shift+A-->搜索registry，找到registry...，然后找到compiler.automake.allow.when.app.running勾选
    2.执行快捷键Ctrl+F9才执行热加载
 */
@SpringBootApplication
public class MusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

}
