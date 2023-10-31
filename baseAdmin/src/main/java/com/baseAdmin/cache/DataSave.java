package com.baseAdmin.cache;

import java.util.Date;

public class DataSave {
	private String id;
	private double value;
	private Date dataTime;

	
	
	public DataSave(String id, double value, Date dataTime) {
		super();
		this.id = id;
		this.value = value;
		this.dataTime = dataTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

}
