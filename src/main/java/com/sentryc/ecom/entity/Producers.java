package com.sentryc.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
public class Producers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @CreationTimestamp
    private Instant createdAt;
}
