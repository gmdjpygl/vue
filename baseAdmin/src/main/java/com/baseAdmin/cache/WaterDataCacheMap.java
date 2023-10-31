package com.baseAdmin.cache;

import java.util.concurrent.ConcurrentHashMap;

import com.baseAdmin.pojo.WaterData;
import com.baseAdmin.util.DateUtil;

public class WaterDataCacheMap {
	private static ConcurrentHashMap<String, WaterData> cacheMap = new ConcurrentHashMap<>();

	/**
	 * 获取缓存的对象
	 * 
	 * @param key
	 * @return
	 */
	public static WaterData getCache(String key) {

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
	public static void putCache(String key, WaterData date) {
		// 一般是进行数据库查询，将查询的结果进行缓存
		cacheMap.put(key, date);
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

	/**
	 * 是否保存(一小时内,相邻数据相同,只保存第一条记录
	 * @param d
	 * @return
	 */
	public static boolean isSave(WaterData d) {
		boolean b = false;
		WaterData cache = getCache(d.getSiteId());
		if (cache != null) {
			long interval = DateUtil.intervalMinute(cache.getDataTime(), d.getDataTime());
			if (interval < 60 // 同一小时内
					&& interval >= 0
					&& DateUtil.getMinute(d.getDataTime()) != 0// 0分时
					&& cache.getDataValue() == d.getDataValue()// 值相同
			) {
				b = false;
			} else {
				b = true;
			}
		} else {
			b = true;
		}
		if (b) {
			putCache(d.getSiteId(), d);
		}
		return b;
	}
}
