package com.sentryc.ecom.repository;

import com.sentryc.ecom.entity.Producers;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducersRepository extends JpaRepository<Producers, UUID> {}
