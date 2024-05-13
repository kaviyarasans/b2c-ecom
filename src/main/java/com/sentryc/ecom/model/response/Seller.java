package com.sentryc.ecom.model.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Seller {

    private String sellerName;

    private String externalId;

    private List<ProducerSellerState> producerSellerStates;

    private String marketplaceId;
}
