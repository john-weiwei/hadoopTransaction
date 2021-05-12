package com.cn.allen.mapper;

import com.cn.allen.dto.GoodsDTO;
import com.cn.allen.entity.Goods;
import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDTO record);

    Goods selectByPrimaryKey(Integer id);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);

    int update(Goods record);
}