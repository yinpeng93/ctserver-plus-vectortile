package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 16:49
 * @Description:
 */
public interface CityMapper extends BaseMapper<City> {

    List<String> queryCityNamesByPrefixAdminCode(@Param("adminCode") String adminCode);
}
