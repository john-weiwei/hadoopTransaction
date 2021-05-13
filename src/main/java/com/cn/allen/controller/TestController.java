package com.cn.allen.controller;

import com.alibaba.fastjson.JSON;
import com.cn.allen.dto.GoodsDTO;
import com.cn.allen.entity.Goods;
import com.cn.allen.service.IZgGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/4/29
 * @Description:
 */
@Api(tags = "缓存实战-商品信息")
@RestController
@RequestMapping("/goods")
public class TestController {

    Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private IZgGoodsService zgGoodsService;

    @ApiOperation(value = "商品列表")
    @GetMapping("/")
    public Object listGoods() {
        List<Goods> goodsList = zgGoodsService.listGoods();
        return JSON.toJSONString(goodsList);
    }

    @ApiOperation(value = "商品详情")
    @GetMapping(value = "/detail/{id}")
    public Object detailGoods(@PathVariable("id") int id) {
        Goods goods = zgGoodsService.queryById(id);
        return JSON.toJSONString(goods);
    }

    @ApiOperation(value = "新增")
    @PostMapping(value = "/")
    public Object add(@ApiParam(value = "商品信息",required = true) @RequestBody GoodsDTO goodsDTO) {
        log.info("input params{}:",JSON.toJSONString(goodsDTO));
        int result = zgGoodsService.insertGood(goodsDTO);
        if (result == 1 ){
            return 1;
        }
        return 0;
    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping("/delete/{id}")
    public Object del(@PathVariable("id")int id) {
        int result = zgGoodsService.delGood(id);
        if (result == 1 ){
            return 1;
        }
        return 0;
    }

    @ApiOperation(value = "更新商品信息")
    @PutMapping("/{id}")
    public Object update(@ApiParam(value = "商品id")@PathVariable("id")int id,
                         @ApiParam(value = "商品信息") @RequestBody Goods goods) {
        goods.setId(id);
        int result = zgGoodsService.updateGood(goods);
        if (result == 1 ){
            return 1;
        }
        return 0;
    }

    @ApiOperation(value = "新增测试数据")
    @GetMapping("/insertTestData")
    public Object insertTestData() {
        int num = 3;
        while (true) {
            log.info("新增{}条数据",num-2);
            GoodsDTO goodsDTO = new GoodsDTO();
            goodsDTO.setGoodCode("992847294"+num);
            goodsDTO.setGoodCount(10);
            goodsDTO.setGoodName("phone"+num);
            zgGoodsService.insertGood(goodsDTO);
            num++;
        }
    }
}
