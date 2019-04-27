package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.ProvinceBL;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/4/27 0027 09:28
 * @Description:
 */
public interface ProvinceBLMapper extends BaseMapper<ProvinceBL> {

    List<ProvinceBL> queryProvinceBLList();
}
