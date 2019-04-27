package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.BackgroundPolyline;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/4/24 0024 16:16
 * @Description:
 */
public interface IBackgroundPolylineService extends IService<BackgroundPolyline> {
    List<BackgroundPolyline> queryBackgroundPolylineList(String tableName);

    void insertBackgroundPolylineList(String fromTableName, String toTableName, String prefixAdminCode);

    void deleteBackgroundPolylineList(String tableName,String prefixAdminCode);
}
