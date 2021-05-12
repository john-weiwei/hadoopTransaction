package com.cn.allen.service;

import com.cn.allen.dto.GoodsDTO;
import com.cn.allen.entity.Goods;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/5/7
 * @Description: 缓存穿透
 * 访问数据库中不存在的数据，导致大量请求到mysql数据库，增大数据库压力
 */
@CacheConfig(cacheNames = "goods")
@Service("zgGoodsService")
public class GoodsServiceImpl4 extends ZgGoodServiceImpl implements IZgGoodsService {

    private static final Logger log = LoggerFactory.getLogger(GoodsServiceImpl4.class);
    //数据量大，要做到去重
    private BloomFilter<String> bf = null; //等效成一个set集合

    @PostConstruct
    public void init() {
        List<Goods> goods = this.listGoods();
        bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),goods.size());
        for (Goods g: goods
             ) {
            bf.put(g.getId()+"");
        }
    }

    @CachePut(key = "'key'+#goods.id")
    @Override
    public int insertGood(GoodsDTO goods) {
        int result = super.insertGood(goods);
        bf.put("id");//将id放入布隆过滤器
        return result;
    }

    @Cacheable
    @Override
    public Goods queryById(int id) {
        //判断是否存在
        if (!bf.mightContain(String.valueOf(id))) {
            log.info("非法访问");
            return null;
        }
        log.info("从数据库获取数据");
        return super.queryById(id);
    }
}
