package com.jspiders.springmvc6.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springmvc6.base.pojo.StudentPojo;
import com.jspiders.springmvc6.base.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;

	public StudentPojo add(String name, String email, 
							long contact, String city, String username, String password) {
		StudentPojo student = repository.add(name, email, contact, city, username, password);
		return student;
	}
	
	public StudentPojo login(String username, String password) {
		StudentPojo student = repository.login(username, password);
		return student;
	}

	public StudentPojo search(int id) {
		StudentPojo student = repository.search(id);
		return student;
	}

	public List<StudentPojo> searchAll() {
		List<StudentPojo> students = repository.searchAll();
		return students;
	}

	public StudentPojo remove(int id) {
		StudentPojo student = repository.remove(id);
		return student;
	}

	public StudentPojo update(int id, String name, String email, long contact, String city, String username,
			String password) {
		StudentPojo student = repository.update(id, name, email, contact, city, username, password);
		return student;
	}

}