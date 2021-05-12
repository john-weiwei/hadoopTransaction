package com.cn.allen.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/4/29
 * @Description:
 */
public class ArticleServiceImpl implements IArticleService {

    @CacheEvict
    @Override
    public Object getArticleDetail() {
        return null;
    }

    @CachePut(key = "#article",value = "article001")
    @Override
    public Object publishArticle(String article) {
        return null;
    }
}
