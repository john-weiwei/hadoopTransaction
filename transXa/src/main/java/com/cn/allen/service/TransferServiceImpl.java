package com.cn.allen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/5/13
 * @Description:
 */
//@Service
public class TransferServiceImpl implements ITransferService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //默认事务传播特性：required
    @Transactional
    @Override
    public String transfer(int money) {
        int resultJames = jdbcTemplate.update("INSERT INTO bank_a(money,user_name)VALUES (?,?)",-money,"james");
        int resultPeter = jdbcTemplate.update("INSERT INTO bank_b(money,user_name)VALUES (?,?)",money,"peter");
        if (money > 20){
            throw new RuntimeException("money too large");
        }
        return resultJames+"-"+resultPeter;
    }
}
