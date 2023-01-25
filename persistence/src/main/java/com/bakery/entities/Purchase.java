package com.bakery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPurchase;

    @Column(nullable = false, name = "full_purchase")
    private int fullPurchase;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date datePurchase;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseDetail>purchaseDetailList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
