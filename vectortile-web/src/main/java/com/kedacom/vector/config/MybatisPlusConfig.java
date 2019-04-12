package com.kedacom.vector.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//配置项目中对应的mapper类扫描路径
@MapperScan("com.kedacom.vector.mapper")
public class MybatisPlusConfig {
    /**
     * 相当于顶部的：
     * {@code @MapperScan("com.kedacom.kfront.ctserver.poi..mapper*")}
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
//        scannerConfigurer.setBasePackage("com.kedacom.kfront.ctserver.datafilter.mapper*");
//        return scannerConfigurer;
//    }

    /**
     * mybatis-plus 分页插件
     */

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("postgresql");
        return page;
    }

    /**
     * sequence
     * @return
     */
    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration();
        conf.setKeyGenerator(new PostgreKeyGenerator());
        return conf;
    }

    /**
     * SQL执行效率插件
     */
//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }
}
