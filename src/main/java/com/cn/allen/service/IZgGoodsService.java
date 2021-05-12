package com.cn.allen.service;

import com.cn.allen.dto.GoodsDTO;
import com.cn.allen.entity.Goods;

import java.util.List;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/4/30
 * @Description:
 */
public interface IZgGoodsService {

    int insertGood(GoodsDTO goods);

    int delGood(int id);

    int updateGood(Goods goods);

    List<Goods> listGoods();

    Goods queryById(int id);
}
