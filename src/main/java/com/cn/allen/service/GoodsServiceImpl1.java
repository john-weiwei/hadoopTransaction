package com.cn.allen.service;

import com.alibaba.fastjson.JSON;
import com.cn.allen.dto.GoodsDTO;
import com.cn.allen.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/5/6
 * @Description: 实现redis缓存
 */

//@Service("zgGoodsService")
public class GoodsServiceImpl1 extends ZgGoodServiceImpl implements IZgGoodsService{

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public int insertGood(GoodsDTO goods) {
        return 0;
    }

    @Override
    public int delGood(int id) {
        String key = "key"+id;
        redisTemplate.delete(key);
        super.delGood(id);
        redisTemplate.delete(key);
        return 0;
    }

    @Override
    public int updateGood(Goods goods) {
        String key = "key"+goods.getId();
        redisTemplate.delete(key); //双删
        super.updateGood(goods);
        redisTemplate.delete(key);
        return 0;
    }

    @Override
    public List<Goods> listGoods() {
        return null;
    }

    /**
     * 先从缓存中拿数据，拿不到数据再从数据库读取，放入缓存
     * @param id
     * @return
     */
    @Override
    public Goods queryById(int id) {
        String key = "key"+id;
        Object result = null;
        result = redisTemplate.opsForValue().get(key);
        if (!Objects.equals(null,result)) {
            System.out.println("从缓存拿数据");
            return JSON.toJavaObject((JSON) JSON.parse(JSON.toJSONString(result)),Goods.class);
        }
        result = super.queryById(id);
        if (!Objects.equals(null,result)) {
            redisTemplate.opsForValue().set(key,result);
            redisTemplate.expire(key,60000, TimeUnit.MILLISECONDS); //20s 过期
        }
        return JSON.toJavaObject((JSON) JSON.parse(JSON.toJSONString(result)),Goods.class);
    }
}
