package com.aiwl.pms.service;

import java.util.Map;

import com.aiwl.pms.entity.GoodsDetail;

public interface GoodsService {

	Map<String, Object> getGoodsList(GoodsDetail goodsDetail);

	int delGoods(GoodsDetail goodsDetail);

	int saveGoods(GoodsDetail goodsDetail);

}
