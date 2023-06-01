package com.retail.store.retailStore;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.store.retailStore.controller.RetailStoreController;
import com.retail.store.retailStore.model.*;
import com.retail.store.retailStore.service.DiscountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RetailStoreControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DiscountService discountService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RetailStoreController retailStoreController = new RetailStoreController(discountService);
        mockMvc = MockMvcBuilders.standaloneSetup(retailStoreController).build();
    }

    @Test
    public void testCalculateNetPayableAmount_Success() throws Exception {
        BillGenerationRequest request = new BillGenerationRequest();
        request.setUserType(UserType.CUSTOMER);
        request.setLongTermCustomer(true);
        request.setItems(Arrays.asList(
                createItem("Item 1", 2, 10.0, Category.GROCERY),
                createItem("Item 2", 3, 15.0, Category.OTHER),
                createItem("Item 3", 1, 5.0, Category.GROCERY)
        ));

        BillResponse response = new BillResponse();
        response.setTotalAmount(50.0);
        response.setNetPaybleAmount(45.0);
        response.setTotalDiscount(5.0);
        response.setUserType(UserType.CUSTOMER);
        response.setLongTermCustomer(true);
        response.setItems(request.getItems());

        when(discountService.calculateNetPayableAmount(any(BillGenerationRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/calculateNetPayableAmount")
                .contentType("application/json")
                .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount").value(50.0))
                .andExpect(jsonPath("$.netPaybleAmount").value(45.0))
                .andExpect(jsonPath("$.totalDiscount").value(5.0))
                .andExpect(jsonPath("$.userType").value("CUSTOMER"))
                .andExpect(jsonPath("$.longTermCustomer").value(true))
                .andExpect(jsonPath("$.items[0].name").value("Item 1"))
                .andExpect(jsonPath("$.items[1].name").value("Item 2"))
                .andExpect(jsonPath("$.items[2].name").value("Item 3"));
    }


    // Helper method to convert an object to JSON string
    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to create an Item object
    private Item createItem(String name, int quantity, double price, Category category) {
        Item item = new Item();
        item.setName(name);
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setCategory(category);
        return item;
    }
}

