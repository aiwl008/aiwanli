package com.aiwl.pms.service;

import com.aiwl.pms.entity.User;

public interface UserService {

	User findUser(String userName, String password);

}
