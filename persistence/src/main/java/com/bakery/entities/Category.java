package com.bakery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;
    @Column(nullable = false, length = 20, name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryProduct")
    @ToString.Exclude
    @JsonIgnore
    private List<Product>productList = new ArrayList<>();

}
