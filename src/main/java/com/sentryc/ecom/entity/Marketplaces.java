package com.sentryc.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Marketplaces {

    @Id
    private String id;

    private String description;
}
