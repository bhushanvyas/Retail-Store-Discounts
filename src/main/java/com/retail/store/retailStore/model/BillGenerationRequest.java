package com.retail.store.retailStore.model;

import java.util.List;

public class BillGenerationRequest {
    private UserType userType;
    private boolean longTermCustomer;
    private List<Item> items;

    public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public UserType getUserType() {
        return userType;
    }

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setLongTermCustomer(boolean longTermCustomer) {
		this.longTermCustomer = longTermCustomer;
	}

	public boolean isLongTermCustomer() {
        return longTermCustomer;
    }

}
