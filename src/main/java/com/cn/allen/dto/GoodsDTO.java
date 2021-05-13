package com.cn.allen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/5/6
 * @Description:
 */

@Data
@ApiModel(value = "goodsDTO",description = "商品信息传输实体")
public class GoodsDTO {

    private Integer id;
    @ApiModelProperty(value = "商品编码")
    private String goodCode;

    @ApiModelProperty(value = "商品名称")
    private String goodName;

    @ApiModelProperty(value = "商品数量")
    private Integer goodCount;
}
