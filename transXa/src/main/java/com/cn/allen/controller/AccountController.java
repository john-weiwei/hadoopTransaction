package com.cn.allen.controller;

import com.cn.allen.service.ITransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/5/13
 * @Description:
 */
@Api(tags = "账户业务")
@RestController
public class AccountController {

    @Autowired
    private ITransferService transferService;

    @ApiOperation("转账事务")
    @PutMapping("/transfer")
    public String transferAccount(int money) {

        return transferService.transfer(money);
    }
}
