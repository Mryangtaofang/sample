package com.yang.jvm.classloader;


/**
 * # 这里介绍Class.forName   和  ClassLoader.loadClass() 的区别
 * 
 * 	Class.forName 会对对象进行初始化，而ClassLoader.loadClass()不会
 *	Class.forName还有一个重载的方法，如下：
 *	Class.forName(name, initialize, loader)
 *  initialize设置为true或者是false将决定对象是否被初始化
 *  
 *  
 *  在我们熟悉的Spring框架中的IOC的实现就是使用的ClassLoader。
 *
 *	而在我们使用JDBC时通常是使用Class.forName()方法来加载数据库连接驱动。
 *	这是因为在JDBC规范中明确要求Driver(数据库驱动)类必须向DriverManager注册自己。
 */
public class ClassLoaderDemo {

	public static void main(String[] args){
        try {
            Class.forName("com.yang.jvm.ClassInitProcess");
            
            System.out.println("#########分割符(上面是Class.forName的加载过程，下面是ClassLoader的加载过程)##########");
            ClassLoader.getSystemClassLoader().loadClass("com.yang.jvm.ClassInitProcess");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
}


/*
 * Driver类一般都是在静态块中注册自己
 * public class Driver extends NonRegisteringDriver implements java.sql.Driver {  
    // ~ Static fields/initializers  
    // ---------------------------------------------  
   
    //  
    // Register ourselves with the DriverManager  
    //  
    static {  
        try {  
            java.sql.DriverManager.registerDriver(new Driver());  
        } catch (SQLException E) {  
            throw new RuntimeException("Can't register driver!");  
        }  
    }  
   
    // ~ Constructors  
    // -----------------------------------------------------------  
 
     //Construct a new driver and register it with DriverManager 
     //  
     // @throws SQLException 
     //             if a database error occurs. 
     // 
    public Driver() throws SQLException {  
        // Required for Class.forName().newInstance()  
    } 
     
	}
*/
