package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.dto.ProvinceDTO;
import com.kedacom.vector.entity.Province;
import com.kedacom.vector.mapper.ProvinceMapper;
import com.kedacom.vector.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-20
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements IProvinceService {
    @Autowired
    ProvinceMapper provinceMapper;

    @Override
    public Province queryProvinceByPinyin(String pinyin){
        return provinceMapper.queryProvinceByPinyin(pinyin);
    }

    @Override
    public List<ProvinceDTO> queryAllProvince(){
        return provinceMapper.queryAllProvince();
    }
}
