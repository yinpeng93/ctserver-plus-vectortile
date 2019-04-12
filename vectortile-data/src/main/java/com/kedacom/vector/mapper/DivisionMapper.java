package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.Division;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:27
 * @Description:
 */
public interface DivisionMapper extends BaseMapper<Division> {
    List<Division> queryDivisionList(@Param("tableName") String tableName);

    /**
     * 合并行政区域
     * @param tableName
     * @return
     */
    String unionDivision(@Param("tableName") String tableName);

    void insertDivisionList(@Param("fromTableName") String fromTableName,
                            @Param("toTableName") String toTableName,
                            @Param("prefixAdminCode") String prefixAdminCode);

    void deleteDivisionList(@Param("tableName") String tableName,
                            @Param("prefixAdminCode") String prefixAdminCode);
}
