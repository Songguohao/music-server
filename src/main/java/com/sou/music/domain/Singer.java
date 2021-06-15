package com.sou.music.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Singer implements Serializable {

    private Integer id;

    private String name;

    private Byte sex;

    private String img;

    private Date birth;

    private String location;

    private String introduction;
}
