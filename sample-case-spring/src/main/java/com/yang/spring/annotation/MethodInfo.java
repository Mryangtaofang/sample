package com.yang.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 *	注解的interface关键字需要以@符号开头
 *	1.注解方法不能带有参数；
 *	2.注解方法返回值类型限定为：基本类型、String、Enums、Annotation或者是这些类型的数组；
 * 	3.注解方法可以有默认值；
 * 	4.注解本身能够包含元注解，元注解被用来注解其它注解。
 * 
 * 	元注解：
 * 	@Documented  
 * 		指明拥有这个注解的元素可以被javadoc此类的工具文档化。
 *		这种类型应该用于注解那些影响客户使用带注释的元素声明的类型。
 *		如果一种声明使用Documented进行注解，这种类型的注解被作为被标注的程序成员的公共API。
 * 
 * 	@Target
 * 		指明该类型的注解可以注解的程序元素的范围。
 * 		 该元注解的取值可以为TYPE,METHOD,CONSTRUCTOR,FIELD等。
 *		 如果Target元注解没有出现，那么定义的注解可以应用于程序的任何元素
 * 	
 * 	@Inherited 
 * 		指明该注解类型被自动继承。
 * 		如果用户在当前类中查询这个元注解类型并且当前类的声明中不包含这个元注解类型，
 * 		那么也将自动查询当前类的父类是否存在Inherited元注解，这个动作将被重复执行知道这个标注类型被找到，
 * 		或者是查询到顶层的父类。
 * 
 * 	@Retention
 * 		指明了该Annotation被保留的时间长短。
 *		RetentionPolicy取值为SOURCE,CLASS,RUNTIME。
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo{
	
    String author() default "Pankaj";
    
    String date();
    
    int revision() default 1;
    
    String comments();
}

/**
 * Java内建注解
 *
 *	Java提供了三种内建注解。
 *
 *	@Override
 *		当我们想要复写父类中的方法时，我们需要使用该注解去告知编译器我们想要复写这个方法。这样一来当父类中的方法移除或者发生更改时编译器将提示错误信息。
 *
 *	@Deprecated
 *		当我们希望编译器知道某一方法不建议使用时，我们应该使用这个注解。Java在javadoc 中推荐使用该注解，我们应该提供为什么该方法不推荐使用以及替代的方法。
 *
 *	@SuppressWarnings
 *		这个仅仅是告诉编译器忽略特定的警告信息，例如在泛型中使用原生数据类型。它的保留策略是SOURCE（译者注：在源文件中有效）并且被编译器丢弃。
 *
 *	我们来看一个java内建注解的例子参照上边提到的自定义注解。
 */




