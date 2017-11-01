package com.coamctech.admin.generator.utils;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public abstract class CommonUtils {
	public static final char UNDERLINE = '_';
	public static final char MIDDLELINE = '-';
	public static final String PRE_TABLE = "t_";

	/**
	 * 驼峰改下划线
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		return camelToWithline(UNDERLINE, param);
	}

	/**
	 * 下划线改驼峰
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰改中划线
	 * 
	 * @param param
	 * @return
	 */
	public static String camelToMiddleline(String param) {
		return camelToWithline(MIDDLELINE, param);
	}

	public static String getClassNameByTableName(String tableName) {
		if (StringUtils.isNotEmpty(tableName)) {
			tableName = tableName.replace(PRE_TABLE, "");
			return FirstToUpper(underlineToCamel(tableName));
		}
		return "";
	}

	/**
	 * 首字母大写
	 * 
	 * @param param
	 * @return
	 */
	public static String FirstToUpper(String param) {
		if (StringUtils.isNotEmpty(param)) {
			return param.substring(0, 1).toUpperCase() + param.substring(1);
		}
		return null;
	}

	/**
	 * 首字母小写
	 * 
	 * @param param
	 * @return
	 */
	public static String FirstToLower(String param) {
		if (StringUtils.isNotEmpty(param)) {
			return param.substring(0, 1).toLowerCase() + param.substring(1);
		}
		return null;
	}

	private static String camelToWithline(char lineType, String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(lineType);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将带文字说明的枚举转换为map，字段名默认为text
	 * 
	 * @param enumClass
	 *            枚举类
	 * @return
	 */
	public static <E extends Enum<E>> Map<String, String> getEnumMap(
			final Class<E> enumClass) {
		return getEnumMap(enumClass, "text");
	}

	/**
	 * 将带文字说明的枚举转换为map
	 * 
	 * @param enumClass
	 *            枚举类
	 * @param textField
	 *            text字段名
	 * @return
	 */
	public static <E extends Enum<E>> Map<String, String> getEnumMap(
			final Class<E> enumClass, String textField) {
		final Map<String, String> map = new LinkedHashMap<String, String>();
		try {
			for (final E e : enumClass.getEnumConstants()) {
				Method getter = e.getDeclaringClass().getDeclaredMethod(
						"get" + StringUtils.capitalize(textField),
						new Class<?>[0]);
				Object getterReturnValue = getter.invoke(new Object[0], e);
				map.put(e.name(), Objects.toString(getterReturnValue, ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

}
