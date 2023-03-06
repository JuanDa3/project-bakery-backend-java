package com.bakery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @Column(nullable = false, length = 20, name = "name")
    private String name;

    @Column(nullable = false, length = 20, name = "reference")
    private String reference;

    @Column(nullable = false, name = "price")
    private double price;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="unitMeasurement", nullable = false)
    private UnitMeasurement unitMeasurement;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @JsonIgnore
    private List<SaleDetail>saleDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @JsonIgnore
    private List<PurchaseDetail>purchaseDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="provider_id", nullable = false)
    private Provider provider;
}
