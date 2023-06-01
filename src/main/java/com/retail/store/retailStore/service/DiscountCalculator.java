package com.retail.store.retailStore.service;

import com.retail.store.retailStore.model.BillGenerationRequest;

public interface DiscountCalculator {
   public double calculateDiscount(double amount, BillGenerationRequest billGenerationRequest);
}
