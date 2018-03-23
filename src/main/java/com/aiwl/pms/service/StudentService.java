package com.aiwl.pms.service;

import com.aiwl.pms.entity.Student;


public interface StudentService {

	public void saveStudent(Student student);

	public Student findStudent(String userName, String password);

}
