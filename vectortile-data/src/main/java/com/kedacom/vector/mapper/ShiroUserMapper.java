package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kedacom.vector.entity.ShiroUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/14 0014 13:49
 * @Description:
 */
public interface ShiroUserMapper extends BaseMapper<ShiroUser> {
    ShiroUser queryUserByUsername(@Param("username") String username);
}
