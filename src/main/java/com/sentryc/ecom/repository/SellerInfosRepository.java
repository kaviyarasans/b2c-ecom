package com.sentryc.ecom.repository;

import com.sentryc.ecom.entity.SellerInfos;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerInfosRepository extends JpaRepository<SellerInfos, UUID> {

    List<SellerInfos> findAllByNameAndMarketplaceIdIn(String name, List<String> marketplaceIds, Pageable pageable);

    List<SellerInfos> findAllByMarketplaceIdIn(List<String> marketplaceIds, Pageable pageable);

    List<SellerInfos> findAllByName(String name, Pageable pageable);
}
