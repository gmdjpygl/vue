package com.baseAdmin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {

	public static List<Map<String, Object>> toLowerCaseKey(List<Map<String, Object>> list) {
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			for (String key : map.keySet()) {
				Map<String, Object> o = new HashMap<String, Object>();
				o.put(key.toLowerCase(), map.get(key));
				newList.add(o);
			}
		}
		return newList;
	}

}