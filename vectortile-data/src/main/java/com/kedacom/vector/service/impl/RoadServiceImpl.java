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
    public List<Road> queryR31List(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryR31List(tableName,pageSize,pageNo * pageSize);
    }

    @Override
    public List<Road> queryR32List(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryR32List(tableName,pageSize,pageNo * pageSize);
    }

    @Override
    public List<Road> queryR33List(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryR33List(tableName,pageSize,pageNo * pageSize);
    }

    @Override
    public List<Road> queryR34List(String tableName, Integer pageSize, Integer pageNo) {
        return roadMapper.queryR34List(tableName,pageSize,pageNo * pageSize);
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
    public Integer countR31(String tableName) {
        return roadMapper.countR31(tableName);
    }

    @Override
    public Integer countR32(String tableName) {
        return roadMapper.countR32(tableName);
    }

    @Override
    public Integer countR33(String tableName) {
        return roadMapper.countR33(tableName);
    }

    @Override
    public Integer countR34(String tableName) {
        return roadMapper.countR34(tableName);
    }

    @Override
    public List<Road> queryRZDList(String tableName) {
        return roadMapper.queryRZDList(tableName);
    }

    @Override
    public List<Road> queryRLJList(String tableName) {
        return roadMapper.queryRLJList(tableName);
    }
}
