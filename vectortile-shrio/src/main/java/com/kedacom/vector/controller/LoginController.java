package com.kedacom.vector.controller;

import com.kedacom.common.response.ResponseMessage;
import com.kedacom.vector.module.LoginModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Levin
 * @since 2018/6/28 0028
 */
@RestController
@Slf4j
public class LoginController {

    @GetMapping(value = "/hello")
    public String hello() {
        log.info("不登录也可以访问...");
        return "hello...";
    }

    @GetMapping(value = "/index")
    public String index() {
        log.info("登陆成功了...");
        return "index";
    }

    @GetMapping(value = "/denied")
    public String denied() {
        log.info("小伙子权限不足,别无谓挣扎了...");
        return "denied...";
    }

    @PostMapping(value = "/login")
    public ResponseMessage login(@RequestBody @Valid LoginModule loginModule) {

        String username = loginModule.getUsername();
        String password = loginModule.getPassword();
        // 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟 shiro 的拦截地址内．不然后会报空指针
        Subject sub = SecurityUtils.getSubject();
        // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
        // 认证执行者交由 com.battcn.config.AuthRealm 中 doGetAuthenticationInfo 处理
        // 当以上认证成功后会向下执行,认证失败会抛出异常
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            sub.login(token);
        } catch (UnknownAccountException e) {
            log.error("对用户[{}]进行登录验证,验证未通过,用户不存在", username);
            token.clear();
            return ResponseMessage.error("UnknownAccountException");
        } catch (LockedAccountException lae) {
            log.error("对用户[{}]进行登录验证,验证未通过,账户已锁定", username);
            token.clear();
            return ResponseMessage.error("LockedAccountException");
        } catch (ExcessiveAttemptsException e) {
            log.error("对用户[{}]进行登录验证,验证未通过,错误次数过多", username);
            token.clear();
            return ResponseMessage.error("ExcessiveAttemptsException");
        } catch (AuthenticationException e) {
            log.error("对用户[{}]进行登录验证,验证未通过,堆栈轨迹如下", username, e);
            token.clear();
            return ResponseMessage.error("AuthenticationException");
        } catch (Exception e){
            log.error("登录异常！",e);
            return ResponseMessage.error(e.getMessage());
        }
        return ResponseMessage.ok("登录成功！");
    }
}
