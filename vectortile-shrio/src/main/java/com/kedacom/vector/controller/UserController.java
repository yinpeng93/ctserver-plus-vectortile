package com.kedacom.vector.controller;

import com.kedacom.common.enums.LockEnum;
import com.kedacom.common.response.ResponseMessage;
import com.kedacom.common.utils.PasswordGeneratorUtil;
import com.kedacom.vector.entity.ShiroUser;
import com.kedacom.vector.service.IShiroUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    IShiroUserService shiroUserService;

    @GetMapping
    public String get() {
        return "get.....";
    }

    /**
     * RequiresRoles 是所需角色 包含 AND 和 OR 两种
     * RequiresPermissions 是所需权限 包含 AND 和 OR 两种
     *
     * @return msg
     */
    @RequiresRoles(value = {"admin", "test"}, logical = Logical.OR)
    //@RequiresPermissions(value = {"user:list", "user:query"}, logical = Logical.OR)
    @GetMapping("/query")
    public String query() {
        return "query.....";
    }

    @GetMapping("/find")
    @RequiresRoles(value = {"admin"})
    public String find() {
        return "find.....";
    }

    @GetMapping("/account")
    public ResponseMessage getAccount(@RequestParam("username") String username){
        ResponseMessage responseMessage = null;
        if(!checkUsername(username)){
            return ResponseMessage.error("用户名不是公司邮箱!");
        }
        ShiroUser user = shiroUserService.queryUserByUsername(username);
        if(user != null){
            return ResponseMessage.error("用户已存在！");
        }
        user = buildUser(username);

        boolean flag = false;
        try {
            flag = shiroUserService.insert(user);
            if(!flag) {
                return ResponseMessage.error("用户新增失败！");
            }
            responseMessage = ResponseMessage.ok(user);
        } catch (Exception e) {
            log.error("新增用户报错！",e);
            responseMessage = ResponseMessage.error("新增用户报错！");
        }
        return responseMessage;
    }

    private boolean checkUsername(String username) {
        String regex = "^\\w+@kedacom.com$";
        return username.matches(regex);
    }

    private ShiroUser buildUser(String username) {
        ShiroUser user = new ShiroUser();
        user.setUsername(username);
        user.setPassword(PasswordGeneratorUtil.generatePassword(12));
        user.setRolename("admin");
        user.setLocked(LockEnum.UN_LOCKED.getValue());
        return user;
    }
}
