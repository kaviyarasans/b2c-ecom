package com.sentryc.ecom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
public class Sellers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "producerId")
    private UUID producerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(insertable = false, updatable = false, name = "producerId", referencedColumnName = "id")
    private Producers producers;

    @Column(name = "sellerInfoId")
    private UUID sellerInfoId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "sellerInfoId")
    private SellerInfos sellerInfos;

    @NotNull @Column(name = "state", columnDefinition = "varchar(255) default 'REGULAR'")
    @Enumerated(EnumType.STRING)
    private SellerStatus state;
}
