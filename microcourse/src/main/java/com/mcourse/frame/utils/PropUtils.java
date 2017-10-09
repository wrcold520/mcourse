package com.mcourse.frame.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title 属性文件获取属性值的工具类
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/05/23 16:47:55
 */
public class PropUtils {

	public static final Logger logger = LoggerFactory.getLogger(PropUtils.class);

	private static Map<String, Map<String, String>> propMap = new LinkedHashMap<String, Map<String, String>>();

	/**
	 * 加载配置文件
	 * 
	 * @param filepath
	 *            文件路径
	 * @param alias
	 *            当前属性文件别名
	 * @param trim
	 *            value是否trim
	 */
	public static void use(String filepath, String alias, boolean trim) {
		Assert.hasLength(filepath);
		propMap.clear();

		try {
			InputStream in = new FileInputStream(filepath);
			Properties prop = new Properties();
			prop.load(in);

			Map<String, String> cntPropMap = new LinkedHashMap<String, String>();

			Set<Object> keySet = prop.keySet();
			for (Object keyObj : keySet) {
				if (ObjectUtils.isNotBlank(keyObj)) {
					String key = keyObj.toString();
					cntPropMap.put(key, trim ? prop.getProperty(key).trim() : prop.getProperty(key));
				}
			}

			propMap.put(alias, cntPropMap);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取资源文件的属性值
	 * 
	 * @param alias
	 *            加载属性文件时使用的别名
	 * @param key
	 *            属性key
	 * @return
	 */
	public static String getString(String alias, String key) {
		Map<String, String> aliasPropMap = propMap.get(alias);
		if (aliasPropMap == null) {
			logger.warn("Current property files doesn't contails alias " + alias
					+ ", you could load your property file like this: PropUtils.use(filepath, alias, trim)...");
			return null;
		} else {
			return aliasPropMap.get(key);
		}

	}

	/**
	 * 获取资源文件的属性值
	 * 
	 * @param alias
	 *            加载属性文件时使用的别名
	 * @param key
	 *            属性key
	 * @return
	 */
	public static Integer getInt(String alias, String key) {
		String value = getString(alias, key);
		if (value == null) {
			return null;
		} else {
			return Integer.valueOf(value);
		}
	}

	/**
	 * 获取资源文件的属性值
	 * 
	 * @param alias
	 *            加载属性文件时使用的别名
	 * @param key
	 *            属性key
	 * @return
	 */
	public static Long getLong(String alias, String key) {
		String value = getString(alias, key);
		if (value == null) {
			return null;
		} else {
			return Long.valueOf(value);
		}
	}

}
