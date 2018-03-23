package com.aiwl.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aiwl.pms.entity.Student;
import com.aiwl.pms.mapper.StudentMapper;
import com.aiwl.pms.service.StudentService;

@Controller
public class StudentController {

//	@Autowired(required = false)
	@Autowired
	private StudentService studentService;
	@RequestMapping(value="save",method = RequestMethod.GET)
	public String saveStudent(HttpServletRequest request,HttpServletResponse response){
		Student student = new Student();
		student.setAge(27);
		student.setName("∑Îº—”Ì");
		studentService.saveStudent(student);
		return "login/login";
	}
}
