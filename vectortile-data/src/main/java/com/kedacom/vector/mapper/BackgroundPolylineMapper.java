package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.BackgroundPolyline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/4/24 0024 16:15
 * @Description:
 */
public interface BackgroundPolylineMapper extends BaseMapper<BackgroundPolyline> {

    List<BackgroundPolyline> queryBackgroundPolylineList(@Param("tableName") String tableName);

    void insertBackgroundPolylineList(@Param("fromTableName") String fromTableName,
                                      @Param("toTableName") String toTableName,
                                      @Param("prefixAdminCode") String prefixAdminCode);

    void deleteBackgroundPolylineList(@Param("tableName") String tableName,
                                      @Param("prefixAdminCode") String prefixAdminCode);
}
