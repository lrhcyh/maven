package com.abcft.common.util.gen;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实例操作工具类
 * 
 * @author mike <br>
 *         2015年12月30日
 * @version 1.0
 */
public final class BeanUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(BeanUtil.class);
	
	private BeanUtil(){}

	/**
	 * 判断这个bean中是否存在此属性,如果存在就返回这个属性，不存在就返回null
	 * 
	 * @param bean
	 * @param fieldName
	 * @return Field
	 */
	public static Field isField(Object bean, String fieldName) {
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 通过属性名给bean中的属性赋值
	 * 
	 * 如果access 设置为false ,是私有的方法的时候，就会抛出异常
	 * 
	 * @param bean
	 * @param fieldName
	 * @param fieldValue
	 * @param access
	 */
	public static void setField(Object bean, String fieldName, Object fieldValue, boolean access) {
		Field field = isField(bean, fieldName);
		if (null == field) {
			LOG.error("the bean exclusive field :" + fieldName);
			throw new RuntimeException("the bean exclusive field :" + fieldName);
		}
		setField(bean, field, fieldValue, access);
	}

	/**
	 * 通过属性名给bean中的属性赋值
	 * 
	 * @param bean
	 * @param fieldName
	 * @param fieldValue
	 */
	public static void setField(Object bean, String fieldName, Object fieldValue) {
		setField(bean, fieldName, fieldValue, true);
	}

	/**
	 * 给bean的属性赋值
	 * 
	 * @param bean
	 * @param field
	 * @param value
	 * @param access
	 */
	public static void setField(Object bean, Field field, Object value, boolean access) {
		try {
			if (!field.isAccessible() && access)
				field.setAccessible(true);
			field.set(bean, value);
		} catch (SecurityException e) {
			LOG.error("set field with bean error", e);
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			LOG.error("set field with bean error", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			LOG.error("set field with bean error", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 给bean的属性赋值，默认是有权限的
	 * 
	 * @param bean
	 * @param field
	 * @param value
	 * @param access
	 */
	public static void setField(Object bean, Field field, Object value) {
		setField(bean, field, value, true);
	}

	/**
	 * 返回属性的值
	 * 
	 * @param field
	 * @param bean
	 * @return <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Object bean, Field field) {
		T value = null;
		try {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			value = (T) field.get(bean);
		} catch (SecurityException e) {
			LOG.error("get field value error", e);
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			LOG.error("get field value error", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			LOG.error("get field value error", e);
			throw new RuntimeException(e);
		}
		return value;
	}

	/**
	 * 根据属性名返回属性的值
	 * 
	 * @param field
	 * @param bean
	 * @return <T>
	 */
	public static <T> T getFieldValue(Object bean, String fieldName) {
		Field field = isField(bean, fieldName);
		if (null == field) {
			LOG.error("the bean exclusive field :" + fieldName);
			throw new RuntimeException("the bean exclusive field :" + fieldName);
		}
		return getFieldValue(bean, field);
	}

	/**
	 * bean复制所有除静态以外的属性到target
	 * 
	 * @param bean
	 * @param target
	 */
	public static void copyFields(Object bean, Object target) {
		for (Field field : bean.getClass().getDeclaredFields()) {
			if (!Modifier.isStatic(field.getModifiers())) {
				copyField(bean, target, field);
			}
		}
	}

	/**
	 * 从bean复制一个属性到target中
	 * 
	 * @param bean
	 * @param target
	 * @param field
	 */
	public static void copyField(Object bean, Object target, Field field) {
		try {
			field.setAccessible(true);
			field.set(target, field.get(bean));
		} catch (SecurityException e) {
			LOG.error("copy field value error", e);
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			LOG.error("copy field value error", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			LOG.error("copy field value error", e);
			throw new RuntimeException(e);
		}
	}

}
