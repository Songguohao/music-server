package com.sou.music.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 管理员
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

    private Integer id;

    private String username;

    private String password;
}
