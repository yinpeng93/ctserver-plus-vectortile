package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Road;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-19
 */
public interface IRoadService extends IService<Road> {
    List<Road> queryRoadList(String tableName,Integer pageSize,Integer pageNo);

    Integer countRoad(String tableName);

    void insertRoadList(String fromTableName, String toTableName, String prefixAdminCode);

    void deleteRoadList(String tableName, String prefixAdminCode,String prefixAdminCodeDelete);

    List<Road> queryR22List(String tableName, Integer pageSize, Integer pageNo);

    List<Road> queryR21List(String tableName, Integer pageSize, Integer pageNo);

    List<Road> queryR1List(String tableName, Integer pageSize, Integer pageNo);

    List<Road> queryR31List(String tableName, Integer pageSize, Integer pageNo);

    List<Road> queryR32List(String tableName, Integer pageSize, Integer pageNo);

    List<Road> queryR33List(String tableName, Integer pageSize, Integer pageNo);

    List<Road> queryR34List(String tableName, Integer pageSize, Integer pageNo);

    Integer countR1(String tableName);

    Integer countR21(String tableName);

    Integer countR22(String tableName);

    Integer countR31(String tableName);

    Integer countR32(String tableName);

    Integer countR33(String tableName);

    Integer countR34(String tableName);


}
