package com.bakery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
    private Category categoryProduct;

    @ManyToOne
    @JoinColumn(name="unitMeasurement", nullable = false)
    private UnitMeasurement unitMeasurement;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<SaleDetail>saleDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<PurchaseDetail>purchaseDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="provider_id", nullable = false)
    private Provider provider;
}
