package com.sentryc.ecom.unitTest.service;

import static org.mockito.Mockito.*;

import com.sentryc.ecom.entity.SellerInfos;
import com.sentryc.ecom.model.request.PageInput;
import com.sentryc.ecom.model.request.SellerFilter;
import com.sentryc.ecom.model.request.SellerSortBy;
import com.sentryc.ecom.repository.SellerInfosRepository;
import com.sentryc.ecom.repository.SellersRepository;
import com.sentryc.ecom.service.SellerService;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {

    @InjectMocks
    private SellerService sellerService;

    @Mock
    private SellerInfosRepository sellerInfosRepository;

    @Mock
    private SellersRepository sellersRepository;

    @Test
    public void testGetSellers() {

        PageInput pageInput = PageInput.builder().page(0).size(20).build();
        PageRequest pageRequest = PageRequest.of(0, 20);
        when(sellerInfosRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(List.of(getSellerInfos())));

        sellerService.getSellers(new SellerFilter(), pageInput, SellerSortBy.NAME_ASC);

        verify(sellerInfosRepository, times(1)).findAll(pageRequest);
        verify(sellersRepository, times(1))
                .findAllBySellerInfoIdIn(Set.of(getSellerInfos().getId()));
    }

    private static SellerInfos getSellerInfos() {
        return SellerInfos.builder()
                .name("Gem Clothings")
                .marketplaceId("amazon.ae")
                .externalId("amazon.ae.gem")
                .country("USA")
                .url("gemstores.com")
                .id(UUID.fromString("c9786789-d94a-4b20-bd3c-85c410cba2e5"))
                .build();
    }
}
