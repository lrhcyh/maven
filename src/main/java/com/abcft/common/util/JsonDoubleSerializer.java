package com.abcft.common.util;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Gson转换时,Double转式化
 * @author inning
 * @DateTime 2015-7-20 上午11:37:39
 *
 */
public class JsonDoubleSerializer implements JsonSerializer<Double> {
	
	private String doublePattern;
	
	public JsonDoubleSerializer(String doublePattern) {
		this.doublePattern = doublePattern;
	}
		
	@Override
	public JsonElement serialize(Double arg0, Type arg1,
			JsonSerializationContext arg2) {
		return new JsonPrimitive(new DecimalFormat(doublePattern).format(arg0));
	}
}