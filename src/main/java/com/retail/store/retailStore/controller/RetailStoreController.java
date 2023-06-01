package com.retail.store.retailStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.store.retailStore.model.BillGenerationRequest;
import com.retail.store.retailStore.model.BillResponse;
import com.retail.store.retailStore.service.DiscountService;

@RestController
@RequestMapping("/api")
public class RetailStoreController {
    private final DiscountService discountService;

    @Autowired
    public RetailStoreController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/calculateNetPayableAmount")
    public BillResponse calculateNetPayableAmount(@RequestBody BillGenerationRequest billGenerationRequest) {
        return discountService.calculateNetPayableAmount(billGenerationRequest);
    }
}