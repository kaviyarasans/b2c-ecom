package com.sentryc.ecom.controller;

import com.sentryc.ecom.model.request.PageInput;
import com.sentryc.ecom.model.request.SellerFilter;
import com.sentryc.ecom.model.request.SellerSortBy;
import com.sentryc.ecom.model.response.Seller;
import com.sentryc.ecom.model.response.SellerPageableResponse;
import com.sentryc.ecom.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @QueryMapping
    public SellerPageableResponse sellers(
            @Argument SellerFilter filter, @Argument PageInput page, @Argument SellerSortBy sortBy) {
        List<Seller> sellerList = sellerService.getSellers(filter, page, sortBy);
        return SellerPageableResponse.builder().data(sellerList).build();
    }
}
