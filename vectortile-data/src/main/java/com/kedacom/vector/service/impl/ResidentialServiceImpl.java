package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Residential;
import com.kedacom.vector.mapper.ResidentialMapper;
import com.kedacom.vector.service.IResidentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 15:59
 * @Description:
 */
@Service
public class ResidentialServiceImpl extends ServiceImpl<ResidentialMapper, Residential> implements IResidentialService {

    @Autowired
    ResidentialMapper residentialMapper;

    @Override
    public List<Residential> queryResidentialList(List<String> cityNames) {
        return residentialMapper.queryResidentialList(cityNames);
    }
}
