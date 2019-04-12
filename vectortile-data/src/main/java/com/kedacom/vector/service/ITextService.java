package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Text;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-19
 */
public interface ITextService extends IService<Text> {
    List<Text> queryTextList(String tableName);

    void insertTextList(String fromTableName,String toTableName,String prefixAdminCode);

    void deleteTextList(String tableName,String prefixAdminCode);
}
