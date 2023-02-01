package com.bakery.repositories;

import com.bakery.entities.Product;
import com.bakery.factory.DtoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select new com.bakery.factory.DtoProduct (p.idProduct,p.amount, p.name,p.price, p.reference,p.categoryProduct.name as nameCategory, p.provider.name as nameProvider, p.unitMeasurement.name as nameUnitMeasurement) from Product p")
    List<DtoProduct>listAllProducts();
}
