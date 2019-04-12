package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.dto.ProvinceDTO;
import com.kedacom.vector.entity.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-20
 */
public interface ProvinceMapper extends BaseMapper<Province> {
    Province queryProvinceByPinyin(@Param("pinyin") String pinyin);

    List<ProvinceDTO> queryAllProvince();
}
