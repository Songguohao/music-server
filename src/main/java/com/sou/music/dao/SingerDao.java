package com.sou.music.dao;

import com.sou.music.domain.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
public interface SingerDao {

    /**
     * 增加
     * @param singer
     */
    int insert(Singer singer);

    /**
     * 修改
     * @param singer
     */
    int update(Singer singer);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据主键查询
     */
    Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     */
    List<Singer> allSinger();

    /**
     * 根据歌手的名字模糊查询列表
     */
    List<Singer> singerOfName(@Param("name") String name);

    /**
     * 根据歌手的名性别查询列表
     */
    List<Singer> singerOfSex(Byte sex);
}
