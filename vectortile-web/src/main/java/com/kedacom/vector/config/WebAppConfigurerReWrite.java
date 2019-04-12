package com.kedacom.vector.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * All rights Reserved, Designed By MR.DING.NC
 *
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 解决跨域问题
 * @author: MR.DING.NC
 * @date: ${date} ${time}
 * @Copyright: ${year} MR.DING.NC Inc. All rights reserved.
 */
@Configuration
public class WebAppConfigurerReWrite extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")//允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
                .allowedMethods("*")//允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等
                .allowedHeaders("*")//允许所有的请求header访问，可以自定义设置任意请求头信息
                .maxAge(3600)
                .allowCredentials(false);
    }
}
