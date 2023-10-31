package com.baseAdmin.pojo;

import java.util.Date;

public class RainData {
	private String dataId;
	private String siteId;
	private double dataValue;
	private Date dataTime;

	public RainData(String dataId, String siteId, double dataValue, Date dataTime) {
		super();
		this.dataId = dataId;
		this.siteId = siteId;
		this.dataValue = dataValue;
		this.dataTime = dataTime;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public double getDataValue() {
		return dataValue;
	}

	public void setDataValue(double dataValue) {
		this.dataValue = dataValue;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

}
