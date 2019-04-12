package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.ShiroUser;
import com.kedacom.vector.mapper.ShiroUserMapper;
import com.kedacom.vector.service.IShiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/14 0014 13:56
 * @Description:
 */
@Service
public class ShiroUserImpl extends ServiceImpl<ShiroUserMapper,ShiroUser> implements IShiroUserService {
    @Autowired
    ShiroUserMapper userMapper;

    @Override
    public ShiroUser queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }
}
