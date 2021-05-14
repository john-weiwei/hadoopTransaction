package com.cn.allen;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/5/14
 * @Description: 开启lcn事务
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class TmApplication {
    public static void main(String[] args) {
        SpringApplication.run(TmApplication.class,args);
    }
}
