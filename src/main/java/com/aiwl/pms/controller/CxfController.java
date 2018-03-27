package com.aiwl.pms.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.aiwl.pms.entity.User;

@Component("restSample")
public class CxfController {
	 	@GET  
	    @Path("/order")   
	    @Produces({ MediaType.APPLICATION_JSON })  
	    public User findUser() {  
	 		User p = new User();
	        p.setUsername("张三");
	        return p;
	    }  
	 	
	 	@GET  
	    @Path("/getUser")   
	    @Produces({ MediaType.APPLICATION_XML })  
	    public User getUser(@QueryParam("name") String name  ) {  
	 		User p = new User();
	        p.setUsername("张三");
	        System.out.println("name = "+name);
	        return p;
	    }
}
