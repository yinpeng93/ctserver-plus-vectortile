package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.City;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 16:50
 * @Description:
 */
public interface ICityService extends IService<City> {

    List<String> queryCityNamesByPrefixAdminCode(String adminCode);
}
