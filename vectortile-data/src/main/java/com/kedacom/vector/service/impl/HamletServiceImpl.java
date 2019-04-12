package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Hamlet;
import com.kedacom.vector.mapper.HamletMapper;
import com.kedacom.vector.service.IHamletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-19
 */
@Service
public class HamletServiceImpl extends ServiceImpl<HamletMapper, Hamlet> implements IHamletService {

    @Autowired
    HamletMapper hamletMapper;

    @Override
    public List<Hamlet> queryHamletsByTableName(String tableName) {
        return hamletMapper.queryHamletListByTableName(tableName);
    }

    @Override
    public Hamlet queryHamletByGid(String gid) {
        return hamletMapper.queryHamletByGid(gid);
    }

    @Override
    public List<Hamlet> queryHamletListByTableNameAndLikeAdminCode(String tableName,String admincode) {
        return hamletMapper.queryHamletListByTableNameAndLikeAdminCode(tableName,admincode + "%");
    }

    @Override
    public void insertHamletList(String fromTableName, String toTableName, String prefixAdminCode) {
        hamletMapper.insertHamletList(fromTableName, toTableName, prefixAdminCode);
    }

    @Override
    public void deleteHamletList(String tableName, String prefixAdmincode) {
        hamletMapper.deleteHamletList(tableName, prefixAdmincode);
    }


}
