package com.baseAdmin.pojo;

public class JsonData {
	private String plantInfo;
	private String data;
	private String timeStamp;
	private String sign;

	
	
	public JsonData(String plantInfo, String data, String timeStamp, String sign) {
		super();
		this.plantInfo = plantInfo;
		this.data = data;
		this.timeStamp = timeStamp;
		this.sign = sign;
	}

	public String getPlantInfo() {
		return plantInfo;
	}

	public void setPlantInfo(String plantInfo) {
		this.plantInfo = plantInfo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
