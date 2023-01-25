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
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmployee;

    @Column(nullable = false, length = 50, name = "name")
    private String name;

    @Column(nullable = false, length = 20, name = "identification")
    private String identification;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @ToString.Exclude
    private List<Telephone> telephoneList = new ArrayList<>();

    @OneToOne(mappedBy = "employee")
    private User user;
}
