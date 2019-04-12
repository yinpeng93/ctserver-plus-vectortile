package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.Building;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 13:53
 * @Description:
 */
public interface BuildingMapper extends BaseMapper<Building> {
    List<Building> queryBuildingListByPage(@Param("prefixAdminCode") String prefixAdminCode,
                                     @Param("pageSize") Integer pageSize,
                                     @Param("offset") Integer offset);

    List<Building> queryBuildingList(@Param("prefixAdminCode") String prefixAdminCode);
}
