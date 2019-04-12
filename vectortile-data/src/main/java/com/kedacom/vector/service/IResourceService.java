package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Resource;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/26 0026 10:55
 * @Description:
 */
public interface IResourceService extends IService<Resource> {
    Resource queryByDataScopeAndCoordinateSystem(String adminId,String system);
}
