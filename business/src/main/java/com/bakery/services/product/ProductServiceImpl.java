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
    public Product registerProduct(Product product) throws ExceptionRegister {
        validateNull(product);
        productRepository.save(product);
        return product;
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

    private void validateNull(Product product){
        if (product == null){
            throw new ExceptionRegister("Product can't be null");
        }
    }
}
