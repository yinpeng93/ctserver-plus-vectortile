package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Text;
import com.kedacom.vector.mapper.TextMapper;
import com.kedacom.vector.service.ITextService;
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
public class TextServiceImpl extends ServiceImpl<TextMapper, Text> implements ITextService {

    @Autowired
    TextMapper textMapper;

    @Override
    public List<Text> queryTextList(String tableName) {
        return textMapper.queryTextList(tableName);
    }

    @Override
    public void insertTextList(String fromTableName, String toTableName, String prefixAdminCode) {
        textMapper.insertTextList(fromTableName, toTableName, prefixAdminCode);
    }

    @Override
    public void deleteTextList(String tableName, String prefixAdminCode) {
        textMapper.deleteTextList(tableName, prefixAdminCode);
    }
}
