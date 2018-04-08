package com.aiwl.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.net.ftp.FtpClient;

import com.aiwl.common.utils.FtpUtil;
import com.aiwl.common.utils.PropertiesUtil;
import com.aiwl.common.utils.StringUtils;
import com.aiwl.common.utils.UuidUtil;
import com.aiwl.pms.entity.GoodsDetail;
import com.aiwl.pms.service.GoodsClassService;
import com.aiwl.pms.service.GoodsService;


@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
 	private FtpUtil ftputil;
	
	@Autowired
	private GoodsClassService goodsClassService;
	
	@RequestMapping(value = "skipToGoodsList")
	public String skipToGoodsDetailList(HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> parentClass = goodsClassService.getChildrenClass();
		List<Map<String, Object>> parentGoods = goodsService.getParentGoods();
		request.setAttribute("childrenClass", parentClass);
		request.setAttribute("parentGoods", parentGoods);
		return "goodsList";
	}
	
	@RequestMapping(value = "getGoodsList")
	public @ResponseBody Map<String,Object> getGoodsList(HttpServletRequest request,HttpServletResponse response,GoodsDetail goodsDetail){
		return goodsService.getGoodsList(goodsDetail);
	}
	
	@RequestMapping(value = "delGoods")
	public @ResponseBody String delGoods(HttpServletRequest request,HttpServletResponse response,GoodsDetail goodsDetail){
		 int num = goodsService.delGoods(goodsDetail);
		 if(num >0){
			 return "success";
		 }
		 return "error";
	}
	
	//保存
	@RequestMapping("saveGoods")
	public @ResponseBody String saveGoods(GoodsDetail goodsDetail, HttpServletRequest request, String inspectionTime1) {
		try {
			
			String propertiesFilePath = GoodsController.class.getClassLoader().getResource("/").getPath() + "ftp.properties";
			Map<String, String> map = PropertiesUtil.read(propertiesFilePath);
			String url = map.get("ftp.host");
			//上传商品图片1
			if(StringUtils.isNotEmpty(goodsDetail.getGoodsdetailsimage1())  && !goodsDetail.getGoodsdetailsimage1().startsWith("http")){
				
				FtpClient ftp = FtpUtil.create(map);
				String pictureDir = "image/goods/" + UuidUtil.getUUID() + "." +goodsDetail.getGoodsdetailsimage1Type();
				String pictureurl = ftputil.upload(goodsDetail.getGoodsdetailsimage1(), ftp, pictureDir);
				goodsDetail.setShowimage(url+pictureurl);
				goodsDetail.setGoodsdetailsimage1(url+pictureurl);
			}else{
				goodsDetail.setGoodsdetailsimage1(null);
			}
			
			//上传商品图片2
			if(StringUtils.isNotEmpty(goodsDetail.getGoodsdetailsimage2())  && !goodsDetail.getGoodsdetailsimage2().startsWith("http")){
				FtpClient ftp = FtpUtil.create(map);
				String pictureDir = "image/goods/" + UuidUtil.getUUID() +  "." +goodsDetail.getGoodsdetailsimage2Type();
				String pictureurl = ftputil.upload(goodsDetail.getGoodsdetailsimage2(), ftp, pictureDir);
				goodsDetail.setGoodsdetailsimage2(url+pictureurl);
			}else{
				goodsDetail.setGoodsdetailsimage2(null);
			}
			
			//上传商品图片3
			if(StringUtils.isNotEmpty(goodsDetail.getGoodsdetailsimage3())  && !goodsDetail.getGoodsdetailsimage3().startsWith("http")){
				FtpClient ftp = FtpUtil.create(map);
				String pictureDir = "image/goods/" + UuidUtil.getUUID() + "." +goodsDetail.getGoodsdetailsimage3Type();
				String pictureurl = ftputil.upload(goodsDetail.getGoodsdetailsimage3(), ftp, pictureDir);
				goodsDetail.setGoodsdetailsimage3(url+pictureurl);
			}else{
				goodsDetail.setGoodsdetailsimage3(null);
			}
			
			if("0".equals(goodsDetail.getGoodsparentid().toString())){
				goodsDetail.setGoodsparentid(null);
			}
			int num = goodsService.saveGoods(goodsDetail);
			if(num>0){
				return "success";
			}
			return "error";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
