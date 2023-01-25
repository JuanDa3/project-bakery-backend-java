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
public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvider;

    @Column(nullable = false, length = 50, name = "name")
    private String name;

    @Column(nullable = false, length = 20, name = "identification")
    private String identification;

    @OneToMany(mappedBy = "provider")
    @ToString.Exclude
    private List<PurchaseDetail>purchaseDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @ToString.Exclude
    private List<Telephone>telephoneList = new ArrayList<>();

    @OneToMany(mappedBy = "provider")
    @ToString.Exclude
    private List<Product>productList = new ArrayList<>();
}
