package com.kedacom.vector.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/14 0014 13:37
 * @Description: 用户表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shiro_user")
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色名
     */
    private String rolename;

    /**
     * 是否禁用,1禁用，0可用
     */
    private Integer locked;
}
