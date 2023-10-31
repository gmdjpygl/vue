package com.baseAdmin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServerPropertiesUtil {
	private static Properties p = null;
	// 读取配置文件且加载数据库驱动
	static {
		// 实例化一个properties对象用来解析我们的配置文件
		p = new Properties();
		// 通过类加载器来读取我们的配置文件，以字节流的形式读取
		InputStream in = ServerPropertiesUtil.class.getClassLoader().getResourceAsStream("application.yml");
		try {
			// 将配置文件自如到Propreties对象，来进行解析
			p.load(in);
			// 读取配置文件
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static String getPropertyValue(String key) {
		String v = p.getProperty(key);
		if (v == null) {
			v = "";
		}
		return v.trim();
	}

	public static List<String> getPropertyValueArr(String key) {
		List<String> list = new ArrayList<String>();
		String v = p.getProperty(key);
		if (v != null) {
			String[] arr = v.split(",");
			for (String str : arr) {
				// 拼接前缀
				list.add(str);
			}
		}
		return list;
	}
}
