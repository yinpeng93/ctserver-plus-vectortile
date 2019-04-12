package com.kedacom.vector.service;

import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.ShiroUser;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/14 0014 13:55
 * @Description:
 */
public interface IShiroUserService extends IService<ShiroUser> {
    ShiroUser queryUserByUsername(String username);
}
