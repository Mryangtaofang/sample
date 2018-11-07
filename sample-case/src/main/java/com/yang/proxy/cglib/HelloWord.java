package com.yang.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class HelloWord {

	 public static void main(String[] args) {   
	        
	    	System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
	        Enhancer enhancer = new Enhancer();
	        enhancer.setSuperclass(UserService.class);
	        
	        enhancer.setCallback(new MethodInterceptor() {
				
	        	/**
	        	 * 这里要理解intercept方法的几个参数代表的意思
	             * @proxyobj 指的是代理类对象 == new UserManagerImpl();
	             * @Method 指的是目标类中被拦截的方法 ,比如当调用addUser时，这个Method对象
	             * @args指的是 调用拦截方法所需的参数
	             * @MethodProxy 指的是用来调用目标类被拦截方法的方法，这个方法比反射更快
	        	 */
				@Override
				public Object intercept(Object proxyobj, Method method, Object[] args,MethodProxy proxy) throws Throwable {
					
			        if ("addUser".equals(method.getName()))// 过滤方法   
			        	 System.out.println("先记录操作日志!"); 
			        
			        //调用被代理方法，比如addUser被调用，则这里会调用UserManagerImpl对象的add方法，args为addUser的入参
			        Object obj = proxy.invokeSuper(proxyobj, args);
			        
			        if ("addUser".equals(method.getName()))// 过滤方法   
			        	 System.out.println("在记录操作日志!");  
			        
			        return obj; 
				}
			});
	        
	        UserService proxy= (UserService)enhancer.create();
	        proxy.addUser("12", "root");
	    }
}
