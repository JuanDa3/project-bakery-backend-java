package com.bakery.services.product;

import com.bakery.entities.Product;
import com.bakery.exceptions.*;
import com.bakery.factory.DtoProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void registerProduct(Product product) throws ExceptionRegister;

    void deleteProduct(Integer id) throws ExceptionDelete;

    List<Product> listAllProducts() throws ExceptionList;

    Page<Product>listProductPageable(Pageable pageable) throws ExceptionList;

    Optional<Product>findProductById(Integer id) throws ExceptionFind;

    Product updateProduct(Integer id, Product productToUpdate) throws ExceptionUpdate;
}
