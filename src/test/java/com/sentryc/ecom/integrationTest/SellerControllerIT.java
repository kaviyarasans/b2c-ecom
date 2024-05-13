package com.sentryc.ecom.integrationTest;

import static org.junit.Assert.*;

import com.sentryc.ecom.common.AbstractIntegrationTest;
import com.sentryc.ecom.model.response.ProducerSellerState;
import com.sentryc.ecom.model.response.Seller;
import com.sentryc.ecom.model.response.SellerPageableResponse;
import com.sentryc.ecom.model.response.SellerState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.test.tester.GraphQlTester;

@AutoConfigureGraphQlTester
public class SellerControllerIT extends AbstractIntegrationTest {

    @Autowired
    GraphQlTester graphQlTester;

    @Test
    void testFindSeller() {

        String document =
                """
                        query {
                          sellers(
                            page: {page: 0, size: 10}
                            filter: {producerIds: ["cde77934-d697-4610-a2ee-64387c144f55", "1bf3cf89-f87c-46fc-8605-028ea638b138"], searchByName: "Gem Clothings", marketplaceIds: "amazon.ae"}
                            sortBy: NAME_ASC
                          ) {
                            data {
                              externalId
                              marketplaceId
                              sellerName
                              producerSellerStates {
                                producerId
                                producerName
                                sellerId
                                sellerState
                              }
                            }
                          }
                        }""";
        //
        graphQlTester
                .document(document)
                .execute()
                .path("sellers")
                .entity(SellerPageableResponse.class)
                .satisfies(response -> {
                    assertEquals(1, response.getData().size());

                    Seller responseData = response.getData().get(0);
                    assertEquals("amazon.ae.gem", responseData.getExternalId());
                    assertEquals("amazon.ae", responseData.getMarketplaceId());
                    assertEquals("Gem Clothings", responseData.getSellerName());

                    ProducerSellerState producerSellerFirstRecord =
                            responseData.getProducerSellerStates().get(0);
                    assertEquals("1bf3cf89-f87c-46fc-8605-028ea638b138", producerSellerFirstRecord.getProducerId());
                    assertEquals("NIKE", producerSellerFirstRecord.getProducerName());
                    assertEquals("68d68153-328a-4ee2-a25b-d0d6e47b7f3f", producerSellerFirstRecord.getSellerId());
                    assertEquals(SellerState.REGULAR, producerSellerFirstRecord.getSellerState());

                    ProducerSellerState producerSellerSecondRecord =
                            responseData.getProducerSellerStates().get(1);
                    assertEquals("cde77934-d697-4610-a2ee-64387c144f55", producerSellerSecondRecord.getProducerId());
                    assertEquals("ADIDAS", producerSellerSecondRecord.getProducerName());
                    assertEquals("5076f564-3d64-45b9-afbf-d489b4ae92e3", producerSellerSecondRecord.getSellerId());
                    assertEquals(SellerState.REGULAR, producerSellerSecondRecord.getSellerState());
                });
    }
}
