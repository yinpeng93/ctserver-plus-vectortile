package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.ProvinceBL;
import com.kedacom.vector.mapper.ProvinceBLMapper;
import com.kedacom.vector.service.IProvinceBLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/4/27 0027 10:15
 * @Description:
 */
@Service
public class ProvinceBLServiceImpl extends ServiceImpl<ProvinceBLMapper,ProvinceBL> implements IProvinceBLService {

    @Autowired
    private ProvinceBLMapper provinceBLMapper;

    @Override
    public List<ProvinceBL> queryProvinceBLList() {
        List<ProvinceBL> provinceBLList = provinceBLMapper.queryProvinceBLList();
        return provinceBLList;
    }
}
