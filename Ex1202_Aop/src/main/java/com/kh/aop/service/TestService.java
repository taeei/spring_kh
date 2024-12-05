package com.kh.aop.service;

import dao.TestDAO;

public class TestService {
	
	TestDAO test_dao;
	public void setTest_dao(TestDAO test_dao) {
		this.test_dao = test_dao;
	}
	
	public void test() {
		System.out.println("--TestService.test()--");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
