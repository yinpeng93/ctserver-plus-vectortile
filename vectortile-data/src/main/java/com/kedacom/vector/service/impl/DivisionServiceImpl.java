package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Division;
import com.kedacom.vector.mapper.DivisionMapper;
import com.kedacom.vector.service.IDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:37
 * @Description:
 */
@Service
public class DivisionServiceImpl extends ServiceImpl<DivisionMapper,Division> implements IDivisionService {
    @Autowired
    DivisionMapper divisionMapper;

    @Override
    public List<Division> queryDivisionList(String tableName) {
        return divisionMapper.queryDivisionList(tableName);
    }

    @Override
    public String unionDivision(String tableName) {
        return divisionMapper.unionDivision(tableName);
    }

    @Override
    public void insertDivisionList(String fromTableName, String toTableName, String prefixAdminCode) {
        divisionMapper.insertDivisionList(fromTableName, toTableName, prefixAdminCode);
    }

    @Override
    public void deleteDivisionList(String tableName, String prefixAdminCode) {
        divisionMapper.deleteDivisionList(tableName, prefixAdminCode);
    }
}
