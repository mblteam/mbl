package com.mbl.common.vo;

public class SettleVO {

	private String shopId;
	private String orderTimeBegin;
	private String orderTimeEnd;
	private String balanceType;
	private String shopCode;

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getOrderTimeBegin() {
		return orderTimeBegin;
	}

	public void setOrderTimeBegin(String orderTimeBegin) {
		this.orderTimeBegin = orderTimeBegin;
	}

	public String getOrderTimeEnd() {
		return orderTimeEnd;
	}

	public void setOrderTimeEnd(String orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

}
