package com.sentryc.ecom.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProducerSellerState {

    private String producerId;

    private String producerName;

    private SellerState sellerState;

    private String sellerId;
}
