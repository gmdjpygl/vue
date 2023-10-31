package com.baseAdmin.pojo.Wlw;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WlwData3 {
	private String identity;
	private List<Date> timeStamp;
	private List<Map<String,Object>> resultArr;
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
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
