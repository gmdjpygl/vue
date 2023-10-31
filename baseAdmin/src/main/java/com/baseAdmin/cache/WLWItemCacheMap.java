package com.baseAdmin.cache;

import java.util.concurrent.ConcurrentHashMap;

import com.baseAdmin.pojo.Wlw.WlwItemData;


public class WLWItemCacheMap {
	private static ConcurrentHashMap<String, WlwItemData> cacheMap = new ConcurrentHashMap<>();

	/**
	 * 获取缓存的对象
	 * 
	 * @param key
	 * @return
	 */
	public static WlwItemData getCache(String key) {

		// 如果缓冲中有该账号，则返回value
		if (cacheMap.containsKey(key)) {
			return cacheMap.get(key);
		}
		return null;
	}

	/**
	 * 初始化缓存
	 * 
	 * @param key
	 */
	public static void putCache(String key, WlwItemData date) {
		// 一般是进行数据库查询，将查询的结果进行缓存
		cacheMap.put(key, date);
	}
	public static boolean isNull() {
		if(getSize()==0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 移除缓存信息
	 * 
	 * @param key
	 */
	public static void removeCache(String key) {
		cacheMap.remove(key);
	}

	public static void removeAllCache() {
		cacheMap.clear();
	}

	public static int getSize() {
		return cacheMap.size();
	}
}
