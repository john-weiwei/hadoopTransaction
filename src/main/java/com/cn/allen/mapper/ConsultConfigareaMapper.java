package com.cn.allen.mapper;

import com.cn.allen.entity.ConsultConfigarea;
import java.util.List;

public interface ConsultConfigareaMapper {
    int insert(ConsultConfigarea record);

    List<ConsultConfigarea> selectAll();
}