package com.aiwl.common.utils;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	public static Map<String, String> read(String propertiesFilePath) throws Exception{
		Properties properties = new Properties();
		properties.load(new FileInputStream(propertiesFilePath));
		Map<String, String> map = (Map)properties;
		return map;
	}
	
	public static String get(String propertiesFilePath, String key) throws Exception{
		return read(propertiesFilePath).get(key);
	}
	
}
