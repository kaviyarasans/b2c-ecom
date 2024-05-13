package com.sentryc.ecom.repository;

import com.sentryc.ecom.entity.Marketplaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplacesRepository extends JpaRepository<Marketplaces, String> {}
