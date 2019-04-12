package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.BackgroundPolygon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:31
 * @Description:
 */
public interface BackgroundPolygonMapper extends BaseMapper<BackgroundPolygon> {
    List<BackgroundPolygon> queryBackgroundPolygonList(@Param("tableName")String tableName);

    void insertBackgroundPolygonList(@Param("fromTableName") String fromTableName,
                                     @Param("toTableName") String toTableName,
                                     @Param("prefixAdminCode") String prefixAdminCode);

    void deleteBackgroundPolygonList(@Param("tableName")String tableName,
                                     @Param("prefixAdminCode")String prefixAdminCode);
}
