package com.bakery.services.product;

import com.bakery.entities.Product;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;

import java.util.List;

public interface ProductService {

    String registerProduct(Product product) throws ExceptionRegister;

    String deleteProduct(Product product) throws ExceptionDelete;

    List<Product> listProducts() throws ExceptionList;
}
