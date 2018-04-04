package com.aiwl.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiwl.pms.entity.GoodsClass;
import com.aiwl.pms.entity.GoodsClassExample;
import com.aiwl.pms.mapper.GoodsClassExtMapper;
import com.aiwl.pms.mapper.GoodsClassMapper;
import com.aiwl.pms.service.GoodsClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GoodsClassServiceImpl implements GoodsClassService {

	@Autowired
	private GoodsClassMapper goodsClassMapper;
	
	@Autowired
	private GoodsClassExtMapper goodsClassExtMapper;
	
	@Override
	public int saveGoodsClass(GoodsClass goodsClass) {
		
		
		if(goodsClass.getClassid() == null){
			return goodsClassMapper.insert(goodsClass);
		}else{
			GoodsClassExample gce = new GoodsClassExample();
			gce.createCriteria().andClassidEqualTo(goodsClass.getClassid());
			return goodsClassMapper.updateByExample(goodsClass, gce);
		}
		
	}

	@Override
	public Map<String, Object> getGoodsClassList(GoodsClass goodsClass) {
		PageHelper.startPage(goodsClass.getOffset() / goodsClass.getLimit() + 1, goodsClass.getLimit());
		
		List<Map<String, Object>> list = goodsClassExtMapper.getGoodsClassList(goodsClass);

		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		map.put("rows", page.getList());

		return map;
	}

	@Override
	public int delGoodsClass(GoodsClass goodsClass) {
		
		GoodsClassExample gce = new GoodsClassExample();
		gce.createCriteria().andClassidEqualTo(goodsClass.getClassid());
		int num = goodsClassMapper.deleteByExample(gce);
		return num;
	}

	@Override
	public GoodsClass selectGoodsClassById(GoodsClass goodsClass) {
		return goodsClassMapper.selectByPrimaryKey(goodsClass.getClassid());
	}

	@Override
	public List<Map<String, Object>> getParentClass() {
		
		return goodsClassExtMapper.getParentClass();
	}

}
