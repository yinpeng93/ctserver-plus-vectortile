package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Hamlet;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-19
 */
public interface IHamletService extends IService<Hamlet> {
    List<Hamlet> queryHamletsByTableName(String tableName);

    Hamlet queryHamletByGid(String Gid);

    List<Hamlet> queryHamletListByTableNameAndLikeAdminCode(String tableName,String admincode);

    void insertHamletList(String fromTableName,String toTableName,String prefixAdminCode);

    void deleteHamletList(String tableName,String prefixAdmincode);
}
