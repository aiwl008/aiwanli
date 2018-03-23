package com.aiwl.pms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiwl.pms.entity.Student;
import com.aiwl.pms.mapper.StudentMapper;
import com.aiwl.pms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public void saveStudent(Student student) {
		
		studentMapper.insert(student);
	}

	@Override
	public Student findStudent(String userName, String password) {
		return null;
	}

}
