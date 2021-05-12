package com.cn.allen.service;

import com.cn.allen.dto.GoodsDTO;
import com.cn.allen.entity.Goods;
import com.cn.allen.mapper.GoodsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/4/30
 * @Description: 正常的使用方式
 */
//@Service("zgGoodsService")
public class ZgGoodServiceImpl implements IZgGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public int insertGood(GoodsDTO goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public int delGood(int id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateGood(Goods goods) {
        return goodsMapper.update(goods);
    }

    @Override
    public List<Goods> listGoods() {
        return goodsMapper.selectAll();
    }

    @Override
    public Goods queryById(int id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
}
