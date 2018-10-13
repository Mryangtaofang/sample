package com.yang.proxy;

public class TestProxy {

    public static void main(String[] args) {   
        
        UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());   
        System.out.println("-----------CGLibProxy-------------");   
        userManager.addUser("tom", "root");
        
        System.out.println("-----------JDKProxy-------------");   
        JDKProxy jdkPrpxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager) jdkPrpxy.newProxy(new UserManagerImpl());   
        userManagerJDK.addUser("tom", "root");   
    }   
}
