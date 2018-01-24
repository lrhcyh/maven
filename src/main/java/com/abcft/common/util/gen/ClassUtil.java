package com.abcft.common.util.gen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

/**
 * 支持，帮助类,工具类编写流程
 * 
 * 0.按照某一维度来归类（比如StringSupport中，都是操作字符方法）
 * 
 * 1.提供方法静态，不提供实例化处理
 * 
 * 2.公共方法保证能完成单独操作，如果是协助公共方法的方法修饰符为private
 * 
 * 3.类都有final修饰
 * 
 * @author mike <br>
 *         2015年12月30日
 * @version 1.0
 */
public final class ClassUtil {

	/**
	 * 不允许实例化
	 */
	private ClassUtil() {

	}

	/**
	 * 获取当前线程中的类加载器
	 * 
	 * @return ClassLoader
	 */
	public static ClassLoader getCurrentClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	/**
	 * 获取类路径
	 * 
	 * @return String
	 */
	public static String getClassPath() {
		String classpath = "";
		URL resource = getCurrentClassLoader().getResource("");
		if (resource != null) {
			classpath = resource.getPath();
		}
		return classpath;
	}

	/**
	 * 使用当前类加载器来加载类
	 * 
	 * @param className
	 * @return Object
	 */
	public static Object loadClassInstanceCurrentLoader(String className) {
		return loadClassInstance(getCurrentClassLoader(), className, new Class[0], new Object[0]);
	}

	/**
	 * 加载一个类的新实例
	 * 
	 * @param loader
	 * @param className
	 * @return Object
	 */
	public static Object loadClassInstance(ClassLoader loader, String className) {
		return loadClassInstance(loader, className, new Class[0], new Object[0]);
	}

	/**
	 * 加载一个类的新实例,带有构造方法
	 * 
	 * @param loader
	 * @param className
	 * @param argsType
	 * @param args
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	public static Object loadClassInstance(ClassLoader loader, String className, Class<?>[] argsType, Object[] args) {
		Class<?> clazz;
		Object newInstance;
		try {
			clazz = loader.loadClass(className);
			Constructor constructor = clazz.getConstructor(argsType);
			newInstance = constructor.newInstance(args);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return newInstance;
	}

	/**
	 * 是否为 int,Integer 类型
	 * 
	 * @param type
	 * @return boolean
	 */
	public static boolean isInt(Class<?> type) {
		return type.equals(int.class) || type.equals(Integer.class);
	}

	/**
	 * 是否为 long,Long 类型
	 * 
	 * @param type
	 * @return boolean
	 */
	public static boolean isLong(Class<?> type) {
		return type.equals(long.class) || type.equals(Long.class);
	}

	/**
	 * 是否为 double,Double 类型
	 * 
	 * @param type
	 * @return boolean
	 */
	public static boolean isDouble(Class<?> type) {
		return type.equals(double.class) || type.equals(Double.class);
	}

	/**
	 * 是否为 String 类型
	 * 
	 * @param type
	 * @return boolean
	 */
	public static boolean isString(Class<?> type) {
		return type.equals(String.class);
	}

}
