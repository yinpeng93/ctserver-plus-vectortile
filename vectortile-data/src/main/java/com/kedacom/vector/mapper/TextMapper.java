package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.Text;
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
public interface TextMapper extends BaseMapper<Text> {
    List<Text> queryTextList(@Param("tableName") String tableName);

    void insertTextList(@Param("fromTableName") String fromTableName,
                          @Param("toTableName") String toTableName,
                          @Param("prefixAdminCode") String prefixAdminCode);


    void deleteTextList(@Param("tableName") String tableName,
                          @Param("prefixAdminCode") String prefixAdminCode);
}
