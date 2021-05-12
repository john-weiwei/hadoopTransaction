package com.cn.allen.service;

import com.cn.allen.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/5/7
 * @Description: 缓存雪崩
 *
 */
//@Service("zgGoodsService")
public class GoodsServiceImpl3 extends ZgGoodServiceImpl implements IZgGoodsService{

    private static final Logger log = LoggerFactory.getLogger(GoodsServiceImpl3.class);
    private static final String CACHE_NAME = "redis";
    @Autowired
    private CacheManager cacheManager;
    //存放商品id和锁的关系
    private ConcurrentHashMap<String, Lock> locks = new ConcurrentHashMap<>();

    /**
     * 1、先从缓存中取，有则返回
     * 2、第二个线程进来的时候加锁
     * @param id
     * @return
     */
    @Override
    public Goods queryById(int id) {
        String key = "key"+id;
        Cache.ValueWrapper valueWrapper;
        //1、从缓存获取数据
        valueWrapper = Objects.requireNonNull(cacheManager.getCache(CACHE_NAME)).get(key);
        if (valueWrapper != null) {
            log.info("从缓存中获取数据");
            return (Goods) valueWrapper.get();
        }
        //2、第二个线程进来的时候加锁
        doLock(key);
        try {
            //双重检查
            valueWrapper = Objects.requireNonNull(cacheManager.getCache(CACHE_NAME)).get(key);
            if (valueWrapper != null) {
                log.info("从缓存中获取数据22");
                return (Goods) valueWrapper.get();
            }
            //3、走数据库查询
            Goods result = super.queryById(id);
            if (result != null) {
                Objects.requireNonNull(cacheManager.getCache(CACHE_NAME)).put(key,result);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            releaseLock(key);
        }
    }

    private void releaseLock(String userCode) {
        ReentrantLock lock = (ReentrantLock) locks.get(userCode);
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    private void doLock(String userCode) {
        ReentrantLock newLock = new ReentrantLock();
        //如果已经存在商品，则丢弃新锁；确保每个商品对应的一个锁
        Lock oldLock = locks.putIfAbsent(userCode,newLock);
        if (Objects.equals(oldLock,null)) {
            newLock.lock();
        } else {
            oldLock.lock();
        }
    }
}
