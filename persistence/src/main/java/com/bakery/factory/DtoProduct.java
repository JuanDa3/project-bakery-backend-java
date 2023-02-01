package com.bakery.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class DtoProduct {
    private Integer idProduct;
    private int amount;
    private String name;
    private double price;
    private String reference;

    private String nameCategory;

    private String nameProvider;

    private String nameUnitMeasurement;


}
