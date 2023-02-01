package com.bakery.services.product;

import com.bakery.entities.Product;
import com.bakery.exceptions.*;
import com.bakery.factory.DtoProduct;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product registerProduct(Product product) throws ExceptionRegister;

    void deleteProduct(Integer id) throws ExceptionDelete;

    List<DtoProduct> listProducts() throws ExceptionList;

    Optional<Product>findProductById(Integer id) throws ExceptionFind;

    Product updateProduct(Integer id, Product productToUpdate) throws ExceptionUpdate;
}
