package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.dto.ProvinceDTO;
import com.kedacom.vector.entity.Province;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-20
 */
public interface IProvinceService extends IService<Province> {
    Province queryProvinceByPinyin(String pinyin);

    List<ProvinceDTO> queryAllProvince();
}
