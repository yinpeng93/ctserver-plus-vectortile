package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.City;
import com.kedacom.vector.mapper.CityMapper;
import com.kedacom.vector.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 16:51
 * @Description:
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {
    @Autowired
    CityMapper cityMapper;

    @Override
    public List<String> queryCityNamesByPrefixAdminCode(String adminCode) {
        return cityMapper.queryCityNamesByPrefixAdminCode(adminCode);
    }
}
