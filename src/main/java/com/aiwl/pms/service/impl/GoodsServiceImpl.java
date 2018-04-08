package com.aiwl.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiwl.pms.entity.GoodsDetail;
import com.aiwl.pms.entity.GoodsDetailExample;
import com.aiwl.pms.mapper.GoodsDetailMapper;
import com.aiwl.pms.mapper.GoodsExtMapper;
import com.aiwl.pms.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service

public class GoodsServiceImpl implements GoodsService{

	@Autowired
	private GoodsExtMapper goodsExtMapper;
	
	@Autowired
	private GoodsDetailMapper goodsDetailMapper;
	
	
	@Override
	public Map<String, Object> getGoodsList(GoodsDetail goodsDetail) {
		PageHelper.startPage(goodsDetail.getOffset() / goodsDetail.getLimit() + 1, goodsDetail.getLimit());
		
		List<Map<String, Object>> list = goodsExtMapper.getGoodsList(goodsDetail);

		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		map.put("rows", page.getList());

		return map;
	}

	@Override
	public int delGoods(GoodsDetail goodsDetail) {
		GoodsDetailExample gde = new GoodsDetailExample();
		gde.createCriteria().andGoodsidEqualTo(goodsDetail.getGoodsid());
		int num = goodsDetailMapper.deleteByExample(gde);
		return num;
	}

	@Transactional
	@Override
	public int saveGoods(GoodsDetail goodsDetail) {
		if(goodsDetail.getGoodsid() == null){
			
			int num = goodsDetailMapper.insert(goodsDetail);
			return num;
		}else{
			
			int num = goodsDetailMapper.updateByPrimaryKeySelective(goodsDetail);
			return num;
		}
	}

	@Override
	public List<Map<String, Object>> getParentGoods() {
		return goodsExtMapper.getParentGoods();
	}

}
