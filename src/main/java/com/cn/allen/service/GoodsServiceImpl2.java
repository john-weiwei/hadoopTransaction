package com.cn.allen.service;

import com.cn.allen.dto.GoodsDTO;
import com.cn.allen.entity.Goods;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/5/7
 * @Description: spring cache 实现缓存
 * 核心思想：当调用一个缓存方法时，会将方法的参数和返回结果作为键值对放到缓存中，等到下次使用同样的参数调用方法时，就直接从缓存拿数据返回
 * 调用逻辑看 CacheInterceptor
 */
@CacheConfig(cacheNames = "goods")
//@Service("zgGoodsService")
public class GoodsServiceImpl2 extends ZgGoodServiceImpl implements IZgGoodsService {

    @Override
    public int insertGood(GoodsDTO goods) {
        return super.insertGood(goods);
    }

    //每次执行都会清除缓存
    @CacheEvict
    @Override
    public int delGood(int id) {
        return super.delGood(id);
    }

    /**
     * 先删除缓存，再修改数据库
     * 每次执行都会将结果集写入缓存
     * @param goods
     * @return
     */
    @CachePut(key = "'key'+#goods.id")
    @Override
    public int updateGood(Goods goods) {
        return super.updateGood(goods);
    }

    /**
     * 当调用该方法时，会把参数和返回值作为键值对放入缓存，当使用相同的餐宿再次
     * 访问该方法将直接从缓存获取数据，不执行实际的方法，否则执行实际方法
     * @param id
     * @return
     */
    @Cacheable(value = "redis")
    @Override
    public Goods queryById(int id) {
        return super.queryById(id);
    }
}
