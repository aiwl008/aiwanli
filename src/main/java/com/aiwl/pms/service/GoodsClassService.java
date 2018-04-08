package com.aiwl.pms.service;

import java.util.List;
import java.util.Map;

import com.aiwl.pms.entity.GoodsClass;

public interface GoodsClassService {

	public int saveGoodsClass(GoodsClass goodsClass);

	public Map<String, Object> getGoodsClassList(GoodsClass goodsClass);

	public int delGoodsClass(GoodsClass goodsClass);

	public GoodsClass selectGoodsClassById(GoodsClass goodsClass);

	public List<Map<String, Object>> getParentClass();

	public List<Map<String, Object>> getChildrenClass();
}
