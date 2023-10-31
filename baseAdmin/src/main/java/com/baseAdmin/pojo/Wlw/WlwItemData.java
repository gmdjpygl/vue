package com.baseAdmin.pojo.Wlw;


public class WlwItemData {
	private String id;
	private String siteItemId;
	private String itemCode;
	private Double wellDepth;
	private Double equipmentHeight;
	private Double groundHeight;
	private Double wellBottomHeight;
	private Double maxValue;
	private Double minValue;
	private Integer decimals;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSiteItemId() {
		return siteItemId;
	}
	public void setSiteItemId(String siteItemId) {
		this.siteItemId = siteItemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public Double getGroundHeight() {
		return groundHeight;
	}
	public void setGroundHeight(Double groundHeight) {
		this.groundHeight = groundHeight;
	}
	public Double getWellBottomHeight() {
		return wellBottomHeight;
	}
	public void setWellBottomHeight(Double wellBottomHeight) {
		this.wellBottomHeight = wellBottomHeight;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public Integer getDecimals() {
		return decimals;
	}
	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}
	public Double getWellDepth() {
		return wellDepth;
	}
	public void setWellDepth(Double wellDepth) {
		this.wellDepth = wellDepth;
	}
	public Double getEquipmentHeight() {
		return equipmentHeight;
	}
	public void setEquipmentHeight(Double equipmentHeight) {
		this.equipmentHeight = equipmentHeight;
	}
	/**
	 * 水位
	 * @param h
	 * @return
	 */
	public Double getJDYW(Double h) {
		Double v = null;
		if(wellDepth!=null && groundHeight!=null && wellBottomHeight!=null
			&& equipmentHeight!=null && h!=null) {
			v = wellBottomHeight + (groundHeight-wellBottomHeight)-(equipmentHeight+h);
		}
		return v;
	}
	/**
	 * 水深
	 * @param h
	 * @return
	 */
	public Double getXDYW(Double h) {
		Double v = null;
		if(wellDepth!=null && groundHeight!=null && wellBottomHeight!=null
			&& equipmentHeight!=null && h!=null) {
			v = (groundHeight-wellBottomHeight)-(equipmentHeight+h);
			if(v<0&& v>-1) {
				v=0d;
			}
		}
		return v;
	}
	/**
	 * 距井口高度
	 * @param h
	 * @return
	 */
	public Double getJjkgd(Double h) {
		Double v = null;
		if( equipmentHeight!=null && h!=null) {
			v = equipmentHeight+h;
		}
		return v;
	}
}
