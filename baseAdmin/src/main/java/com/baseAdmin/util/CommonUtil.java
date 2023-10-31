package com.baseAdmin.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {

	public static boolean isNull(Object obj) {
		boolean state = false;
		if (obj instanceof String) {
			if (obj == null || obj.equals("")) {
				state = true;
			}
		} else if (obj == null) {
			state = true;
		}
		return state;
	}

	public static boolean compare(String str1, String str2) {
		boolean state = false;
		if (str1 != null && str2 != null && str1.equals(str2)) {
			state = true;
		} else if (str1 == null && str2 == null) {
			state = true;
		}
		return state;
	}

	/**
	 * 比较两个map 只比较相同key的值.不同key不比较
	 * 
	 * @param map1
	 * @param map2
	 * @return
	 */
	public static boolean compareMap(Map<String, Object> map1, Map<String, Object> map2) {
		boolean state = true;
		for (String key1 : map1.keySet()) {
			if (map2.containsKey(key1)) {
				Object map1value = map1.get(key1);
				Object map2value = map2.get(key1);

				if ((map1value == null && map2value != null) || (map1value != null && map2value == null)) {
					state = false;
					break;
				} else if (map1value == null && map2value == null) {
					continue;
				}

				if (map1value instanceof Date) {
					if (((Date) map1value).getTime() != ((Date) map2value).getTime()) {
						state = false;
						break;
					}
				} else {
					if (!map1value.toString().equals(map2value.toString())) {
						state = false;
						break;
					}
				}

			}
		}
		return state;
	}

	/**
	 * List<Map<String,Object>>数组里key转换成小写
	 * 
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> toLowerCaseKey(List<Map<String, Object>> list) {
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> o = new HashMap<String, Object>();
			for (String key : map.keySet()) {
				o.put(key.toLowerCase(), map.get(key));
			}
			newList.add(o);
		}
		//list = null;
		return newList;
	}

	public static List<Map<String, Object>> toUpperCaseKey(List<Map<String, Object>> list) {
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> o = new HashMap<String, Object>();
			for (String key : map.keySet()) {
				o.put(key.toUpperCase(), map.get(key));
			}
			newList.add(o);
		}
		//list = null;
		return newList;
	}

	/**
	 * 带_线的按驼峰进行转换 转换示例(aaa,aaa_bbb,aaa_bbb_ccc 转换为aaa aaaBbb aaaBbbCcc)
	 * 
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> toHumpKey(List<Map<String, Object>> list) {
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Map<String, Object> o = new HashMap<String, Object>();
				for (String key : map.keySet()) {
					String[] keyArr = key.split("_");
					String newKey = "";
					for (int i = 0; i < keyArr.length; i++) {
						String k = keyArr[i].toLowerCase();
						if (i == 0) {
							newKey = k;
						} else {
							if (k != null && k.length() > 0) {
								newKey += k.substring(0, 1).toUpperCase() + k.substring(1);
							}
						}
					}
					o.put(newKey, map.get(key));
				}
				newList.add(o);
			}
		}
		return newList;
	}

}