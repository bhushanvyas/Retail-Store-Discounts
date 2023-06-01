package com.retail.store.retailStore.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.store.retailStore.model.BillGenerationRequest;
import com.retail.store.retailStore.model.BillResponse;
import com.retail.store.retailStore.model.Category;
import com.retail.store.retailStore.model.Item;
import com.retail.store.retailStore.model.UserType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountServiceImplTest {
	
    private DiscountCalculator[] discountCalculators;
    private DiscountServiceImpl discountService;

    @Before
    public void setUp() {
        discountCalculators = new DiscountCalculator[]{mock(DiscountCalculator.class)};
        discountService = new DiscountServiceImpl(discountCalculators);
    }

    @Test
    public void testCalculateNetPayableAmount() {
        // Mock input data
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setName("Item 1");
        item1.setCategory(Category.GROCERY);
        item1.setPrice(10.0);
        item1.setQuantity(2);
        items.add(item1);

        Item item2 = new Item();
        item2.setName("Item 2");
        item2.setCategory(Category.OTHER);
        item2.setPrice(20.0);
        item2.setQuantity(3);
        items.add(item2);

        BillGenerationRequest generationRequest = new BillGenerationRequest();
        generationRequest.setItems(items);
        generationRequest.setLongTermCustomer(false);
        generationRequest.setUserType(UserType.CUSTOMER);

        // Mock the behavior of the discount calculator
        DiscountCalculator calculator = discountCalculators[0];
        when(calculator.calculateDiscount(40.0, generationRequest)).thenReturn(10.0);

        // Expected results
        double totalAmount = 10.0 * 2 + 20.0 * 3;
        double totalGroceryAmount = 10.0 * 2;
        double percentApplicableAmount = totalAmount - totalGroceryAmount;
        double netAmount = discountService.calculateAmountDiscount(percentApplicableAmount + totalGroceryAmount);
        double expectedDiscount = totalAmount - netAmount;

        BillResponse expectedResponse = new BillResponse();
        expectedResponse.setItems(items);
        expectedResponse.setLongTermCustomer(false);
        expectedResponse.setUserType(UserType.CUSTOMER);
        expectedResponse.setTotalAmount(totalAmount);
        expectedResponse.setNetPaybleAmount(netAmount);
        expectedResponse.setTotalDiscount(expectedDiscount);

        // Calculate the actual result
        BillResponse actualResponse = discountService.calculateNetPayableAmount(generationRequest);

        // Verify the results
        assertEquals(expectedResponse.getUserType(), actualResponse.getUserType());
    }

    @Test
    public void testCalculateAmountDiscount() {
        double totalAmount = 100.0;
        double expectedDiscountedAmount = 95.0;

        double actualDiscountedAmount = discountService.calculateAmountDiscount(totalAmount);

        assertEquals(expectedDiscountedAmount, actualDiscountedAmount, 0.01);
    }

    @Test
    public void testCalculateTotalAmount() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setName("Item 1");
        item1.setCategory(Category.GROCERY);
        item1.setPrice(10.0);
        item1.setQuantity(2);
        items.add(item1);

        Item item2 = new Item();
        item2.setName("Item 2");
        item2.setCategory(Category.OTHER);
        item2.setPrice(20.0);
        item2.setQuantity(3);
        items.add(item2);

        double expectedTotalAmount = 10.0 * 2 + 20.0 * 3;

        double actualTotalAmount = discountService.calculateTotalAmount(items);

        assertEquals(expectedTotalAmount, actualTotalAmount, 0.01);
    }

    @Test
    public void testCalculateTotalAmountForGrocery() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setName("Item 1");
        item1.setCategory(Category.GROCERY);
        item1.setPrice(10.0);
        item1.setQuantity(2);
        items.add(item1);

        Item item2 = new Item();
        item2.setName("Item 2");
        item2.setCategory(Category.OTHER);
        item2.setPrice(20.0);
        item2.setQuantity(3);
        items.add(item2);

        double expectedTotalGroceryAmount = 10.0 * 2;

        double actualTotalGroceryAmount = discountService.calculateTotalAmountForGrocery(items);

        assertEquals(expectedTotalGroceryAmount, actualTotalGroceryAmount, 0.01);
    }
}
