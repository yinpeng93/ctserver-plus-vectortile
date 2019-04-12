package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.Poi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-19
 */
public interface PoiMapper extends BaseMapper<Poi> {

    void insertPoiList(@Param("fromTableName") String fromTableName,
                          @Param("toTableName") String toTableName,
                          @Param("prefixAdminCode") String prefixAdminCode);

    void deletePoiList(@Param("tableName") String tableName,
                          @Param("prefixAdminCode") String prefixAdminCode);

    Integer countPoi(@Param("tableName")String tableName);

    List<Poi> queryPoiList(@Param("tableName") String tableName, @Param("pageSize") Integer pageSize,
                           @Param("offset") Integer offset);
}
