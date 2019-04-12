package com.kedacom.vector.module;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 16:10
 * @Description:
 */
@Data
public class LoginModule {
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;
}
