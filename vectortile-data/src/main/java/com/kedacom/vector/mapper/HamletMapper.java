package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.Hamlet;
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
public interface HamletMapper extends BaseMapper<Hamlet> {
    List<Hamlet> queryHamletListByTableName(@Param("tableName") String tableName);

    Hamlet queryHamletByGid(@Param("gid") String gid);

    List<Hamlet> queryHamletListByTableNameAndLikeAdminCode(@Param("tableName") String tableName,@Param("admincode") String admincode);

    void insertHamletList(@Param("fromTableName") String fromTableName,
                          @Param("toTableName") String toTableName,
                          @Param("prefixAdminCode") String prefixAdminCode);


    void deleteHamletList(@Param("tableName") String tableName,
                          @Param("prefixAdminCode") String prefixAdminCode);
}
