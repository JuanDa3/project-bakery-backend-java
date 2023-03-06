package com.bakery.repositories;

import com.bakery.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p")
    List<Product>listAll();

    @Query("select p from Product p")
    Page<Product> listAllPage(Pageable pageable);

    @Query("select p from Product p where p.name = :name and p.reference = :reference")
    Optional<Product> findByNameAndReference(String name, String reference);
}
