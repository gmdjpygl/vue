package com.baseAdmin.pojo.Wlw;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WlwMoniData {
	private List<Date> timeStamp;
	private List<Map<String,Object>> resultArr;
	public List<Date> getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(List<Date> timeStamp) {
		this.timeStamp = timeStamp;
	}
	public List<Map<String, Object>> getResultArr() {
		return resultArr;
	}
	public void setResultArr(List<Map<String, Object>> resultArr) {
		this.resultArr = resultArr;
	}
	
}
