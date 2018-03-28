package com.aiwl.pms.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aiwl.common.utils.RequestUtil;
import com.aiwl.pms.entity.User;
import com.aiwl.pms.service.StudentService;
import com.aiwl.pms.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String saveStudent(HttpServletRequest request,HttpServletResponse response,String userName,String password){
//        password = DigestUtils.md5Hex(password);
        User user = userService.findUser(userName,password);
        if(user!=null){
                request.getSession().setAttribute("userId", user.getId());  
                request.getSession().setAttribute("user", userName);  
                
                return "redirect:" + RequestUtil.retrieveSavedRequest(request);//跳转至访问页面
        }else{
               request.getSession().setAttribute("message", "用户名不存在，请重新登录");
               
               return "index"; 
        }
	}
}
