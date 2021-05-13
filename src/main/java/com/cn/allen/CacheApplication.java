package com.cn.allen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/4/29
 * @Description:
 */
@ComponentScan(value = "com.cn.allen.*")
@SpringBootApplication
@MapperScan(value = "com.cn.allen.mapper")
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class,args);
    }
}
