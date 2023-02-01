package com.bakery.services.product;

import com.bakery.entities.Product;
import com.bakery.exceptions.*;
import com.bakery.factory.DtoProduct;
import com.bakery.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void deleteProduct(Integer id) throws ExceptionDelete {
        productRepository.deleteById(id);
    }

    @Override
    public List<DtoProduct> listProducts() throws ExceptionList {
        System.out.println("response " + productRepository.listAllProducts());
        return productRepository.listAllProducts();
    }

    @Override
    public Optional<Product> findProductById(Integer id) throws ExceptionFind {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new ExceptionFind("Product not exist")));
    }

    @Override
    public Product updateProduct(Integer id, Product productToUpdate) throws ExceptionUpdate {
        validateData(id, productToUpdate);
        return productRepository.save(productToUpdate);
    }

    private void validateNull(Product product){
        if (product == null){
            throw new ExceptionRegister("Product can't be null");
        }
    }

    private void validateData(Integer id, Product productToUpdate){
        Optional<Product> actualProduct = productRepository.findById(id);
        if(!actualProduct.get().getName().equalsIgnoreCase(productToUpdate.getName())){
            throw new RuntimeException("can´t change the name of the product");
        }else if(!actualProduct.get().getReference().equalsIgnoreCase(productToUpdate.getReference())){
            throw new RuntimeException("can´t change the reference of the product");
        }
    }
}
