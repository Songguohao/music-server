package com.sou.music.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private String location;

    private String introduction;
}
