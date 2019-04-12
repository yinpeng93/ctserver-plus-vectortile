package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.BackgroundPolygon;
import com.kedacom.vector.mapper.BackgroundPolygonMapper;
import com.kedacom.vector.service.IBackgroundPolygonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:35
 * @Description:
 */
@Service
public class BackgroundPolygonImpl extends ServiceImpl<BackgroundPolygonMapper,BackgroundPolygon> implements IBackgroundPolygonService {
    @Autowired
    BackgroundPolygonMapper backgroundPolygonMapper;

    @Override
    public List<BackgroundPolygon> queryBackgroundPolygonList(String tableName) {
        return backgroundPolygonMapper.queryBackgroundPolygonList(tableName);
    }

    @Override
    public void insertBackgroundPolygonList(String fromTableName, String toTableName, String prefixAdminCode) {
        backgroundPolygonMapper.insertBackgroundPolygonList(fromTableName, toTableName, prefixAdminCode);
    }

    @Override
    public void deleteBackgroundPolygonList(String tableName, String prefixAdminCode) {
        backgroundPolygonMapper.deleteBackgroundPolygonList(tableName, prefixAdminCode);
    }
}
