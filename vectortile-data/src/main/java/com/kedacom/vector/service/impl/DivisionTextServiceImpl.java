package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.DivisionText;
import com.kedacom.vector.mapper.DivisionTextMapper;
import com.kedacom.vector.service.IDivisionTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:38
 * @Description:
 */
@Service
public class DivisionTextServiceImpl extends ServiceImpl<DivisionTextMapper,DivisionText> implements IDivisionTextService {

    @Autowired
    DivisionTextMapper divisionTextMapper;

    @Override
    public List<DivisionText> queryDivisionTextList(String geojson) {
        return divisionTextMapper.queryDivisionTextList(geojson);
    }
}
