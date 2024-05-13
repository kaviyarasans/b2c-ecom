package com.sentryc.ecom.service;

import com.sentryc.ecom.entity.SellerInfos;
import com.sentryc.ecom.entity.Sellers;
import com.sentryc.ecom.model.request.PageInput;
import com.sentryc.ecom.model.request.SellerFilter;
import com.sentryc.ecom.model.request.SellerSortBy;
import com.sentryc.ecom.model.response.ProducerSellerState;
import com.sentryc.ecom.model.response.Seller;
import com.sentryc.ecom.model.response.SellerState;
import com.sentryc.ecom.repository.SellerInfosRepository;
import com.sentryc.ecom.repository.SellersRepository;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class SellerService {

    @Autowired
    private SellerInfosRepository sellerInfosRepository;

    @Autowired
    private SellersRepository sellersRepository;

    private Predicate<String> isSearchByNameNotPresent =
            (searchByName) -> searchByName == null || searchByName.isBlank();

    public List<Seller> getSellers(SellerFilter filter, PageInput page, SellerSortBy sortBy) {

        List<SellerInfos> sellerInfosList = fetchSellerInfoData(filter, page, sortBy);
        Map<UUID, SellerInfos> sellerInfosMap =
                sellerInfosList.stream().collect(Collectors.toMap(SellerInfos::getId, Function.identity()));

        List<Sellers> sellersList = fetchProducerSellerData(filter, sellerInfosMap);
        Map<UUID, List<Sellers>> sellersMap =
                sellersList.stream().collect(Collectors.groupingBy(Sellers::getSellerInfoId));
        List<Seller> sellerList = buildSellerListForResponse(sellerInfosMap, sellersMap, sortBy);

        log.info("Seller data fetched successfully for given query filter");
        return sellerList;
    }

    private List<SellerInfos> fetchSellerInfoData(SellerFilter filter, PageInput page, SellerSortBy sortBy) {
        PageRequest pageRequest = PageRequest.of(page.getPage(), page.getSize());
        List<SellerInfos> sellerInfosList;

        if (Objects.isNull(filter)) {
            return sellerInfosRepository.findAll(pageRequest).getContent();
        }

        if (isSearchByNameNotPresent.test(filter.getSearchByName())) {
            sellerInfosList = (CollectionUtils.isEmpty(filter.getMarketplaceIds()))
                    ? sellerInfosRepository.findAll(pageRequest).getContent()
                    : sellerInfosRepository.findAllByMarketplaceIdIn(filter.getMarketplaceIds(), pageRequest);
        } else {
            sellerInfosList = (CollectionUtils.isEmpty(filter.getMarketplaceIds()))
                    ? sellerInfosRepository.findAllByName(filter.getSearchByName(), pageRequest)
                    : sellerInfosRepository.findAllByNameAndMarketplaceIdIn(
                            filter.getSearchByName(), filter.getMarketplaceIds(), pageRequest);
        }

        return sellerInfosList;
    }

    private List<Sellers> fetchProducerSellerData(SellerFilter filter, Map<UUID, SellerInfos> sellerInfosMap) {
        if (CollectionUtils.isEmpty(filter.getProducerIds())) {
            return sellersRepository.findAllBySellerInfoIdIn(sellerInfosMap.keySet());
        } else {
            return sellersRepository.findAllBySellerInfoIdInAndProducerIdIn(
                    sellerInfosMap.keySet(), filter.getProducerIds());
        }
    }

    private Sort getSort(SellerSortBy sortBy) {
        // Null check
        if (sortBy == null) return Sort.unsorted();

        return switch (sortBy) {
            case SELLER_INFO_EXTERNAL_ID_ASC -> Sort.by(Sort.Direction.ASC, "externalId");
            case SELLER_INFO_EXTERNAL_ID_DESC -> Sort.by(Sort.Direction.DESC, "externalId");
            case NAME_ASC -> Sort.by(Sort.Direction.ASC, "name");
            case NAME_DESC -> Sort.by(Sort.Direction.DESC, "name");
            case MARKETPLACE_ID_ASC -> Sort.by(Sort.Direction.ASC, "marketplaceId");
            case MARKETPLACE_ID_DESC -> Sort.by(Sort.Direction.DESC, "marketplaceId");
        };
    }

    private static List<Seller> buildSellerListForResponse(
            Map<UUID, SellerInfos> sellerInfosMap, Map<UUID, List<Sellers>> sellersMap, SellerSortBy sortBy) {

        Function<List<Sellers>, List<ProducerSellerState>> buildProducerSellerStateList =
                (producerSellerList) -> producerSellerList.stream()
                        .map(sellers -> ProducerSellerState.builder()
                                .producerId(sellers.getProducerId().toString())
                                .producerName(sellers.getProducers().getName())
                                .sellerState(
                                        SellerState.valueOf(sellers.getState().name()))
                                .sellerId(sellers.getId().toString())
                                .build())
                        .collect(Collectors.toList());

        BiFunction<SellerInfos, List<Sellers>, Seller> buildSellerList =
                (sellerInfo, producerSellerList) -> Seller.builder()
                        .sellerName(sellerInfo.getName())
                        .externalId(sellerInfo.getExternalId())
                        .producerSellerStates(buildProducerSellerStateList.apply(producerSellerList))
                        .marketplaceId(sellerInfo.getMarketplaceId())
                        .build();

        List<Seller> sellerDataList = sellersMap.entrySet().stream()
                .map(entry -> {
                    SellerInfos sellerInfos = sellerInfosMap.get(entry.getKey());
                    return buildSellerList.apply(sellerInfos, entry.getValue());
                })
                .sorted(doSort(sortBy))
                .collect(Collectors.toList());

        return sellerDataList;
    }

    private static Comparator<Seller> doSort(SellerSortBy sortBy) {
        // Null check
        if (sortBy == null) return Comparator.comparing(Seller::getSellerName);

        return switch (sortBy) {
            case SELLER_INFO_EXTERNAL_ID_ASC -> Comparator.comparing(Seller::getExternalId);
            case SELLER_INFO_EXTERNAL_ID_DESC -> Comparator.comparing(Seller::getExternalId)
                    .reversed();
            case NAME_ASC -> Comparator.comparing(Seller::getSellerName);
            case NAME_DESC -> Comparator.comparing(Seller::getSellerName).reversed();
            case MARKETPLACE_ID_ASC -> Comparator.comparing(Seller::getMarketplaceId);
            case MARKETPLACE_ID_DESC -> Comparator.comparing(Seller::getMarketplaceId)
                    .reversed();
        };
    }
}
