package com.aiwl.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodsDetailController {
	@RequestMapping(value = "skipToGoodsList")
	public String skipToGoodsDetailList(HttpServletRequest request,HttpServletResponse response){
		return "goodsList";
	}
}
