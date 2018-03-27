package com.aiwl.pms.webservice;

import javax.jws.WebService;

@WebService
public interface HelloWordService {
	String sayHello(String text);
    
}
