package com.retail.store.retailStore.model;

import java.util.List;

public class BillResponse {
	private double totalAmount;
	private double netPaybleAmount;
	private double totalDiscount;
    private UserType userType;
    private boolean longTermCustomer;
    private List<Item> items;
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getNetPaybleAmount() {
		return netPaybleAmount;
	}
	public void setNetPaybleAmount(double netPaybleAmount) {
		this.netPaybleAmount = netPaybleAmount;
	}
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public boolean isLongTermCustomer() {
		return longTermCustomer;
	}
	public void setLongTermCustomer(boolean longTermCustomer) {
		this.longTermCustomer = longTermCustomer;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
    
}
