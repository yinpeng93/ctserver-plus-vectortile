package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.BackgroundPolygon;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:32
 * @Description:
 */
public interface IBackgroundPolygonService extends IService<BackgroundPolygon> {
    List<BackgroundPolygon> queryBackgroundPolygonList(String tableName);

    void insertBackgroundPolygonList(String fromTableName, String toTableName, String prefixAdminCode);

    void deleteBackgroundPolygonList(String tableName,String prefixAdminCode);
}
