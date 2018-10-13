package com.yang.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



/**  
 * CGLibProxy动态代理类的实例  
 *    
 *   
 */   
public class CGLibProxy implements MethodInterceptor {   
     
    private Object targetObject;// CGLib需要代理的目标对象   
     
    public Object createProxyObject(Object obj) {   
        this.targetObject = obj;   
        Enhancer enhancer = new Enhancer();   
        enhancer.setSuperclass(obj.getClass());   
        enhancer.setCallback(this);   
        Object proxyObj = enhancer.create();   
        return proxyObj;// 返回代理对象   
    }   
     
    public Object intercept(Object proxy, Method method, Object[] args,   
            MethodProxy methodProxy) throws Throwable {   
        Object obj = null;   
        if ("addUser".equals(method.getName())) {// 过滤方法   
            checkPopedom();// 检查权限   
        }   
        obj = method.invoke(targetObject, args);   
        return obj;   
    }   
     
    private void checkPopedom() {   
        System.out.println(".:检查权限  checkPopedom()!");   
    }   
}   