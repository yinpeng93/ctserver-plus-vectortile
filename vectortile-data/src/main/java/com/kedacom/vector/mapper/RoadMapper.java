package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.Road;
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
public interface RoadMapper extends BaseMapper<Road> {

    List<Road> queryRoadList(@Param("tableName") String tableName,
                             @Param("pageSize") Integer pageSize,
                             @Param("offset") Integer offset);

    Integer countRoad(@Param("tableName") String tableName);

    void insertRoadList(@Param("fromTableName") String fromTableName,
                        @Param("toTableName") String toTableName,
                        @Param("prefixAdminCode") String prefixAdminCode);

    void deleteRoadList(@Param("tableName") String tableName,
                        @Param("prefixAdminCode") String prefixAdminCode,
                        @Param("prefixAdminCodeDelete") String prefixAdminCodeDelete);

    List<Road> queryR31List(@Param("tableName") String tableName,
                           @Param("pageSize") Integer pageSize,
                           @Param("offset") Integer offset);

    List<Road> queryR32List(@Param("tableName") String tableName,
                            @Param("pageSize") Integer pageSize,
                            @Param("offset") Integer offset);

    List<Road> queryR33List(@Param("tableName") String tableName,
                            @Param("pageSize") Integer pageSize,
                            @Param("offset") Integer offset);

    List<Road> queryR34List(@Param("tableName") String tableName,
                            @Param("pageSize") Integer pageSize,
                            @Param("offset") Integer offset);

    List<Road> queryR22List(@Param("tableName") String tableName,
                            @Param("pageSize") Integer pageSize,
                            @Param("offset") Integer offset);

    List<Road> queryR21List(@Param("tableName") String tableName,
                            @Param("pageSize") Integer pageSize,
                            @Param("offset") Integer offset);

    List<Road> queryR1List(@Param("tableName") String tableName,
                           @Param("pageSize") Integer pageSize,
                           @Param("offset") Integer offset);

    Integer countR1(@Param("tableName") String tableName);

    Integer countR21(@Param("tableName") String tableName);

    Integer countR22(@Param("tableName") String tableName);

    Integer countR31(@Param("tableName") String tableName);

    Integer countR32(@Param("tableName") String tableName);

    Integer countR33(@Param("tableName") String tableName);

    Integer countR34(@Param("tableName") String tableName);

    List<Road> queryRZDList(@Param("tableName") String tableName);

    List<Road> queryRLJList(@Param("tableName") String tableName);
}
