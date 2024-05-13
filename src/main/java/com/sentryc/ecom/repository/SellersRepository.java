package com.sentryc.ecom.repository;

import com.sentryc.ecom.entity.Sellers;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellersRepository extends JpaRepository<Sellers, UUID> {

    List<Sellers> findAllBySellerInfoIdInAndProducerIdIn(Set<UUID> sellerInfoIds, List<UUID> producerIds);

    List<Sellers> findAllBySellerInfoIdIn(Set<UUID> sellerInfoIds);
}
