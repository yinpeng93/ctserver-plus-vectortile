package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Building;
import com.kedacom.vector.mapper.BuildingMapper;
import com.kedacom.vector.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 13:55
 * @Description:
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public List<Building> queryBuildingList(String prefixAdminCode) {
        return buildingMapper.queryBuildingList(prefixAdminCode);
    }

    @Override
    public List<Building> queryBuildingList(String prefixAdminCode, Integer pageSize, Integer pageNo) {
        return buildingMapper.queryBuildingListByPage(prefixAdminCode,pageSize,pageNo*pageSize);
    }
}
