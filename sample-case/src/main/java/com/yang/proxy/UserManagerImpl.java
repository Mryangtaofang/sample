package com.yang.proxy;

public class UserManagerImpl implements UserManager {

	public void addUser(String id, String password) {
		System.out.println(".: 掉用了UserManagerImpl.addUser()方法！ ");

	}

	public void delUser(String id) {
		System.out.println(".: 掉用了UserManagerImpl.delUser()方法！ ");

	}
}
