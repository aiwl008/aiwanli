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
	 * ɾ������
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
	 * ��Ʒ������
	 * @param request
	 * @param response
	 * @param goodsClass
	 * @return
	 */
	@RequestMapping(value = "saveGoodsClass",method = RequestMethod.POST)
	
	public @ResponseBody String saveGoodsClass(HttpServletRequest request,HttpServletResponse response,GoodsClass goodsClass){
		try {
			log.info("��Ʒ������ӿ�ʼ"+goodsClass.toString());
			
			int num = goodsClassService.saveGoodsClass(goodsClass);
			if(num>0){
				log.info("��Ʒ������ӳɹ�"+goodsClass.toString());
				return "success";
			}
			log.info("��Ʒ�������ʧ��"+goodsClass.toString());
			return "error";
			
		} catch (Exception e) {
			log.error("��Ʒ�������ʧ��"+goodsClass.toString()+"  "+e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
}
