package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.DivisionText;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:34
 * @Description:
 */
public interface IDivisionTextService extends IService<DivisionText> {
    List<DivisionText> queryDivisionTextList(String geojson);
}
