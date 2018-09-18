package com.yang.jvm.classloader;

import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader{

	private String classPath;
	 
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name
                + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;

    }

    /**
     * findClass的默认实现如下：
     *
	 *	protected Class<?> findClass(String name) throws ClassNotFoundException {
	 *	        throw new ClassNotFoundException(name);
	 *	}
	 *
	 *	可以看出，抽象类ClassLoader的findClass函数默认是抛出异常的。而前面我们知道，loadClass在父加载器无法加载类的时候，
	 *	就会调用我们自定义的类加载器中的findeClass函数，因此我们必须要在loadClass这个函数里面实现将一个指定类名称转换为Class对象.
	 *
	 *	如果是是读取一个指定的名称的类为字节数组的话，这很好办。但是如何将字节数组转为Class对象呢？很简单，
	 *	Java提供了defineClass方法，通过这个方法，就可以把一个字节数组转为Class对象啦~
     */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
	
}
