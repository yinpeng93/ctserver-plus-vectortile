package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.BackgroundPolyline;
import com.kedacom.vector.mapper.BackgroundPolylineMapper;
import com.kedacom.vector.service.IBackgroundPolylineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/4/24 0024 16:17
 * @Description:
 */
@Service
public class BackgroundPolylineImpl extends ServiceImpl<BackgroundPolylineMapper,BackgroundPolyline> implements IBackgroundPolylineService {

    @Autowired
    BackgroundPolylineMapper backgroundPolylineMapper;

    @Override
    public List<BackgroundPolyline> queryBackgroundPolylineList(String tableName) {
        return backgroundPolylineMapper.queryBackgroundPolylineList(tableName);
    }

    @Override
    public void insertBackgroundPolylineList(String fromTableName, String toTableName, String prefixAdminCode) {
        backgroundPolylineMapper.insertBackgroundPolylineList(fromTableName, toTableName, prefixAdminCode);
    }

    @Override
    public void deleteBackgroundPolylineList(String tableName, String prefixAdminCode) {
        backgroundPolylineMapper.deleteBackgroundPolylineList(tableName,prefixAdminCode);
    }
}
