package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Road;
import com.kedacom.vector.mapper.RoadMapper;
import com.kedacom.vector.service.IRoadService;
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
public class RoadServiceImpl extends ServiceImpl<RoadMapper, Road> implements IRoadService {
    @Autowired
    RoadMapper roadMapper;

    @Override
    public List<Road> queryRoadList(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryRoadList(tableName,pageSize,pageNo*pageNo);
    }

    @Override
    public Integer countRoad(String tableName) {
        return roadMapper.countRoad(tableName);
    }

    @Override
    public void insertRoadList(String fromTableName, String toTableName, String prefixAdminCode) {
        roadMapper.insertRoadList(fromTableName,toTableName,prefixAdminCode);
    }

    @Override
    public void deleteRoadList(String tableName, String prefixAdminCode,String prefixAdminCodeDelete) {
        roadMapper.deleteRoadList(tableName,prefixAdminCode,prefixAdminCodeDelete);
    }

    @Override
    public List<Road> queryR3List(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryR3List(tableName,pageSize,pageNo * pageSize);
    }

    @Override
    public List<Road> queryR22List(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryR22List(tableName,pageSize,pageNo * pageSize);
    }

    @Override
    public List<Road> queryR21List(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryR21List(tableName,pageSize,pageNo * pageSize);
    }

    @Override
    public List<Road> queryR1List(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryR1List(tableName,pageSize,pageNo * pageSize);
    }

    @Override
    public Integer countR1(String tableName) {
        return roadMapper.countR1(tableName);
    }

    @Override
    public Integer countR21(String tableName) {
        return roadMapper.countR21(tableName);
    }

    @Override
    public Integer countR22(String tableName) {
        return roadMapper.countR22(tableName);
    }

    @Override
    public Integer countR3(String tableName) {
        return roadMapper.countR3(tableName);
    }
}
