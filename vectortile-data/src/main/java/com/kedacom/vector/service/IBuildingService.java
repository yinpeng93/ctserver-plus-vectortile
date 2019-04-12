package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Building;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 13:54
 * @Description:
 */
public interface IBuildingService extends IService<Building> {
    List<Building> queryBuildingList(String prefixAdminCode);

    List<Building> queryBuildingList(String prefixAdminCode,Integer pageSize,Integer pageNo);
}
