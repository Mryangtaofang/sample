package com.yang.proxy.cglib;

public class UserService {

	public void addUser(String id, String password) {
		System.out.println("添加成功! 用户ID:" + id + " ,密码:" + password);
	}

	public void delUser(String id) {
		System.out.println("删除成功! 被删除用户ID:" + id);
	}
	
}
