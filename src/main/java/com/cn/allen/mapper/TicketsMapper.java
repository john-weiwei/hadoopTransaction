package com.cn.allen.mapper;

import com.cn.allen.entity.Tickets;
import java.util.List;

public interface TicketsMapper {
    int deleteByPrimaryKey(Integer ticketid);

    int insert(Tickets record);

    Tickets selectByPrimaryKey(Integer ticketid);

    List<Tickets> selectAll();

    int updateByPrimaryKey(Tickets record);
}