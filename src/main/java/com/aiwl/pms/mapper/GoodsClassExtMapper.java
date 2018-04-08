package com.aiwl.pms.mapper;

import java.util.List;
import java.util.Map;

import com.aiwl.pms.entity.GoodsClass;

public interface GoodsClassExtMapper {

	List<Map<String, Object>> getGoodsClassList(GoodsClass goodsClass);

	List<Map<String, Object>> getParentClass();

	List<Map<String, Object>> getChildrenClass();

}
