package com.bakery.services.product;

import com.bakery.entities.Product;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String registerProduct(Product product) throws ExceptionRegister {
        productRepository.save(product);
        return "Product saved correctly";
    }

    @Override
    public String deleteProduct(Product product) throws ExceptionDelete {
        productRepository.delete(product);
        return "Product deleted";
    }

    @Override
    public List<Product> listProducts() throws ExceptionList {
        return productRepository.findAll();
    }
}
