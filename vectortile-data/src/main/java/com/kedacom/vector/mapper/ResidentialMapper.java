package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.Residential;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 15:31
 * @Description:
 */
public interface ResidentialMapper extends BaseMapper<Residential> {

    List<Residential> queryResidentialList(List<String> cityNames);
}
