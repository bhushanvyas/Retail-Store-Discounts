package com.retail.store.retailStore.service;

import com.retail.store.retailStore.model.BillGenerationRequest;
import com.retail.store.retailStore.model.BillResponse;

public interface DiscountService {
    BillResponse calculateNetPayableAmount(BillGenerationRequest billGenerationRequest);
    public BillResponse transformObject(BillGenerationRequest billGenerationRequest);
}
