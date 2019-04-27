package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.ProvinceBL;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/4/27 0027 09:30
 * @Description:
 */
public interface IProvinceBLService extends IService<ProvinceBL> {

    List<ProvinceBL> queryProvinceBLList();
}
