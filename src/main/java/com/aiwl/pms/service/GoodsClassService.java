package com.aiwl.pms.service;

import java.util.Map;

import com.aiwl.pms.entity.GoodsClass;

public interface GoodsClassService {

	public int saveGoodsClass(GoodsClass goodsClass);

	public Map<String, Object> getGoodsClassList(GoodsClass goodsClass);

	public int delGoodsClass(GoodsClass goodsClass);

	public GoodsClass selectGoodsClassById(GoodsClass goodsClass);
}
