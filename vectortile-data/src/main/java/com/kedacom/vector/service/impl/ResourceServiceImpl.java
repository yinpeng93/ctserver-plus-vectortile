package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Resource;
import com.kedacom.vector.mapper.ResourceMapper;
import com.kedacom.vector.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/26 0026 10:56
 * @Description:
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper,Resource> implements IResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public Resource queryByDataScopeAndCoordinateSystem(String adminId, String system) {
        return selectOne(
                Condition.create()
                .eq("data_scope",adminId)
                .eq("coordinate_system",system)
        );
    }
}
