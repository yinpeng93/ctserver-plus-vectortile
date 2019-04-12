package com.kedacom.vector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * All rights Reserved, Designed By MR.DING.NC
 *
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @author: MR.DING.NC
 * @date: ${date} ${time}
 * @Copyright: ${year} MR.DING.NC Inc. All rights reserved.
 */
@SpringBootApplication
@MapperScan("com.kedacom.vector.mapper")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
