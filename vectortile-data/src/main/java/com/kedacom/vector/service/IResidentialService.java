package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Residential;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 15:58
 * @Description:
 */
public interface IResidentialService extends IService<Residential> {
    List<Residential> queryResidentialList(List<String> cityNames);
}
