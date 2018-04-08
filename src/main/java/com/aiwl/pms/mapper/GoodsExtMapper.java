package com.aiwl.pms.mapper;

import java.util.List;
import java.util.Map;

import com.aiwl.pms.entity.GoodsDetail;

public interface GoodsExtMapper {

	List<Map<String, Object>> getGoodsList(GoodsDetail goodsDetail);

	List<Map<String, Object>> getParentGoods();

}
