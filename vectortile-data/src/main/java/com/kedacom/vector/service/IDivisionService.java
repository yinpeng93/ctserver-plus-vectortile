package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Division;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:33
 * @Description:
 */
public interface IDivisionService extends IService<Division> {
    List<Division> queryDivisionList(String tableName);

    /**
     * 合并行政区域
     * @param tableName
     * @return
     */
    String unionDivision(String tableName);

    void insertDivisionList(String fromTableName,String toTableName,String prefixAdminCode);

    void deleteDivisionList(String tableName,String prefixAdminCode);
}
