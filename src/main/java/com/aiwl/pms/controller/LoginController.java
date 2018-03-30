package com.aiwl.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aiwl.common.redis.JedisClusterFactor;
import com.aiwl.common.utils.RequestUtil;
import com.aiwl.pms.entity.User;
import com.aiwl.pms.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JedisClusterFactor jedisClusterFactor;
	
//	@Value("${sessionTime}")
	private String expireTime = "1800000";
	
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response, User user) {
		try {
			user = userService.findUser(user.getUsername(),user.getPassword());
			if(user!=null){
				String sessionId = request.getSession().getId().toString();
				jedisClusterFactor.getObject().set(sessionId, user.getUsername());
				jedisClusterFactor.getObject().expireAt(request.getSession().toString(), Long.valueOf(expireTime));  
				return "index";//跳转至访问页面
			}else{
				request.setAttribute("message", "用户名不存在，请重新登录");
				return "login"; 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtil.retrieveSavedRequest(request);
		}
	}
}
