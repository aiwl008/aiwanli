package com.aiwl.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiwl.pms.entity.GoodsClass;
import com.aiwl.pms.service.GoodsClassService;

@Controller
public class GoodsClassController {
	private final Logger log = LoggerFactory.getLogger(GoodsClassController.class);
	
	@Autowired
	private GoodsClassService goodsClassService;
	
	@RequestMapping(value = "skipToGoodsClassList")
	public String skipToGoodsClassList(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> parentClass = goodsClassService.getParentClass();
		request.setAttribute("parentClass", parentClass);
		return "goodsClassList";
	}
	
	
	@RequestMapping(value = "getGoodsClassList",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getGoodsClassList(HttpServletRequest request,HttpServletResponse response,GoodsClass goodsClass){
		return goodsClassService.getGoodsClassList(goodsClass);
		
	}
	

	/***
	 * 删除分类
	 * @param request
	 * @param response
	 * @param goodsClass
	 * @return
	 */
	@RequestMapping(value = "delGoodsClass",method = RequestMethod.POST)
	public @ResponseBody String delGoodsClass(HttpServletRequest request,HttpServletResponse response,GoodsClass goodsClass){
		 int num = goodsClassService.delGoodsClass(goodsClass);
		 if(num >0){
			 return "success";
		 }
		 return "error";
	}
	
	/****
	 * 商品类别添加
	 * @param request
	 * @param response
	 * @param goodsClass
	 * @return
	 */
	@RequestMapping(value = "saveGoodsClass",method = RequestMethod.POST)
	
	public @ResponseBody String saveGoodsClass(HttpServletRequest request,HttpServletResponse response,GoodsClass goodsClass){
		try {
			log.info("商品类型添加开始"+goodsClass.toString());
			
			int num = goodsClassService.saveGoodsClass(goodsClass);
			if(num>0){
				log.info("商品类型添加成功"+goodsClass.toString());
				return "success";
			}
			log.info("商品类型添加失败"+goodsClass.toString());
			return "error";
			
		} catch (Exception e) {
			log.error("商品类型添加失败"+goodsClass.toString()+"  "+e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
}
