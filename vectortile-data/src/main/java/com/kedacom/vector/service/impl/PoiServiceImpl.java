package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Poi;
import com.kedacom.vector.mapper.PoiMapper;
import com.kedacom.vector.service.IPoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-19
 */
@Service
public class PoiServiceImpl extends ServiceImpl<PoiMapper, Poi> implements IPoiService {

    @Autowired
    PoiMapper poiMapper;

    @Override
    public void insertPoiList(String fromTableName, String toTableName, String prefixAdminCode) {
        poiMapper.insertPoiList(fromTableName, toTableName, prefixAdminCode);
    }

    @Override
    public void deletePoiList(String tableName, String prefixAdminCode) {
        poiMapper.deletePoiList(tableName, prefixAdminCode);
    }

    @Override
    public Integer countPoi(String tableName) {
        return poiMapper.countPoi(tableName);
    }

    @Override
    public List<Poi> queryPoiList(String tableName, Integer pageSize, Integer pageNo) {
        return poiMapper.queryPoiList(tableName,pageSize,pageSize*pageNo);
    }
}
