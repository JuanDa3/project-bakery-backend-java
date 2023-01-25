package com.bakery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Telephone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTelephone;

    @Column(name = "telephone_number", length = 15, nullable = false)
    private String telephoneNumber;

    @ManyToOne
    @JoinColumn(name = "provider")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;
}
