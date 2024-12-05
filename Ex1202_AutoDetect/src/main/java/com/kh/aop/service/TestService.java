package com.kh.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.DeptDAO;

@Repository("testService")
public class TestService {
	
	DeptDAO dept_dao;
	
	@Autowired
	public TestService( DeptDAO dept_dao ) {
		this.dept_dao = dept_dao;
	}
	
	public void test() {
		System.out.println("-- call testService.test() --");
	}

}
