package com.aiwl.pms.webservice.impl;

import javax.jws.WebService;

import com.aiwl.pms.webservice.HelloWordService;

@WebService(endpointInterface = "com.aiwl.pms.webservice.HelloWordService", serviceName = "HelloWordService")
public class HelloWordServiceImpl implements HelloWordService {

	@Override
	public String sayHello(String text) {
		System.out.println(text);
		return "Hello  " + text;
	}

}
