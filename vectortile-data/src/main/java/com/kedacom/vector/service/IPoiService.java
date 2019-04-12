package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Poi;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-19
 */
public interface IPoiService extends IService<Poi> {

    void insertPoiList(String fromTableName, String toTableName, String prefixAdminCode);

    void deletePoiList(String tableName, String prefixAdminCode);

    Integer countPoi(String tableName);

    List<Poi> queryPoiList(String tableName,Integer pageSize,Integer pageNo);
}
