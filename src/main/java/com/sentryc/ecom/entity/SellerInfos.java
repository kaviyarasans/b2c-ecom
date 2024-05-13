package com.sentryc.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
@Table(
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "UniqueSellerAndMarketplace",
                    columnNames = {"marketplaceId", "externalId"})
        })
public class SellerInfos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "marketplaceId")
    private String marketplaceId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(insertable = false, updatable = false, name = "marketplaceId")
    private Marketplaces marketplaces;

    @NotNull private String name;

    private String url;

    private String country;

    private String externalId;
}
