package com.sou.music.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongVO {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer singerId;

    private String singerName;

    private String name;

    private String introduction;

    private Date createTime;

    private Date updateTime;

    private String img;

    private String lyric;

    private String url;
}
