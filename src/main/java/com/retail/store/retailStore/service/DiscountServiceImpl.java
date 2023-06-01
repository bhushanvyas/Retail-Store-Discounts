package com.retail.store.retailStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.store.retailStore.model.BillGenerationRequest;
import com.retail.store.retailStore.model.BillResponse;
import com.retail.store.retailStore.model.Category;
import com.retail.store.retailStore.model.Item;

@Service
public class DiscountServiceImpl implements DiscountService {
    private static final int DISCOUNT_AMOUNT_PER_HUNDRED = 5;
    private final DiscountCalculator[] discountCalculators;

    @Autowired
    public DiscountServiceImpl(DiscountCalculator[] discountCalculators) {
        this.discountCalculators = discountCalculators;
    }

    @Override
    public BillResponse calculateNetPayableAmount(BillGenerationRequest generationRequest) {
    	BillResponse billResponse = transformObject(generationRequest);
        double totalGroceryAmount = calculateTotalAmountForGrocery(generationRequest.getItems());
        double totalAmount = calculateTotalAmount(generationRequest.getItems());
        double percentApplicableAmount = totalAmount - totalGroceryAmount;

        for (DiscountCalculator calculator : discountCalculators) {
        	percentApplicableAmount -= calculator.calculateDiscount(percentApplicableAmount, generationRequest);
        }        
        double netAmount = calculateAmountDiscount(percentApplicableAmount + totalGroceryAmount);
        billResponse.setTotalAmount(totalAmount);
        billResponse.setNetPaybleAmount(netAmount);
        billResponse.setTotalDiscount(totalAmount - netAmount);
        return billResponse;
    }
    
    public double calculateAmountDiscount(double totalAmount) {
        return  totalAmount - (totalAmount / 100) * DISCOUNT_AMOUNT_PER_HUNDRED;
    }
    
    public double calculateTotalAmount(List<Item> items) {
        double totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        
        return totalAmount;
    }
    
    public double calculateTotalAmountForGrocery(List<Item> items) {
        double totalGroceryAmount = items.stream()
                .filter(item -> item.getCategory() == Category.GROCERY)
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        
        return totalGroceryAmount;
    }

	@Override
	public BillResponse transformObject(BillGenerationRequest billGenerationRequest) {
		BillResponse billResponse = new BillResponse();
		billResponse.setItems(billGenerationRequest.getItems());
		billResponse.setLongTermCustomer(billGenerationRequest.isLongTermCustomer());
		billResponse.setUserType(billGenerationRequest.getUserType());
		return billResponse;
	}
}
