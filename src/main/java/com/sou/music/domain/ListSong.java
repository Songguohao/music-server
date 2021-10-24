package com.sou.music.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListSong {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer songId; // 歌曲id

    private Integer songListId; // 歌单id
}
