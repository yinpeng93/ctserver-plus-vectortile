package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.DivisionText;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:28
 * @Description:
 */
public interface DivisionTextMapper extends BaseMapper<DivisionText> {
    List<DivisionText> queryDivisionTextList(@Param("geojson") String geojson);
}
