package com.retail.store.retailStore.service;

import com.retail.store.retailStore.model.BillGenerationRequest;
import com.retail.store.retailStore.model.UserType;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDiscountCalculator implements DiscountCalculator {
    private static final double DISCOUNT_PERCENTAGE = 0.3;

    @Override
    public double calculateDiscount(double amount, BillGenerationRequest billGenerationRequest) {
        if (billGenerationRequest.getUserType() == UserType.EMPLOYEE) {
            return amount * DISCOUNT_PERCENTAGE;
        }
        return 0;
    }
}