package com.aiwl.pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiwl.pms.entity.User;
import com.aiwl.pms.entity.UserExample;
import com.aiwl.pms.mapper.UserMapper;
import com.aiwl.pms.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUser(String userName, String password) {
		UserExample user = new UserExample();
		user.createCriteria().andUsernameEqualTo(userName).andPasswordEqualTo(password);
		List<User> users =  userMapper.selectByExample(user);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
	}

}
