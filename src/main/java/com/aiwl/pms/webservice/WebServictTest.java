package com.aiwl.pms.webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WebServictTest {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factorBean = new JaxWsProxyFactoryBean();
		factorBean.setServiceClass(HelloWordService.class);
		factorBean.setAddress("http://127.0.0.1:8080/selfStudy/webservice/ws?wsdl");
		HelloWordService impl = (HelloWordService) factorBean.create();
		System.out.println(impl.sayHello("aaaaaaaa"));
		}
}
