package com.bakery.services.product;

import com.bakery.entities.Product;
import com.bakery.exceptions.*;
import com.bakery.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void registerProduct(Product product) throws ExceptionRegister {
        validateNull(product);
        validateRepeated(product);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) throws ExceptionDelete {
        findProductById(id);
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> listAllProducts() throws ExceptionList {
        return productRepository.listAll();
    }

    @Override
    public Page<Product> listProductPageable(Pageable pageable) throws ExceptionList {
        return productRepository.listAllPage(pageable);
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
        if(!actualProduct.orElseThrow(() -> new ExceptionFind("Product Not Exist")).getName().equalsIgnoreCase(productToUpdate.getName())){
            throw new RuntimeException("can´t change the name of the product");
        } else if(!actualProduct.orElseThrow(() -> new ExceptionFind("Product Not Exist")).getReference().equalsIgnoreCase(productToUpdate.getReference())){
            throw new RuntimeException("can´t change the reference of the product");
        }
    }

    private void validateRepeated(Product productToSave){
        Optional<Product>productNameReference = productRepository.findByNameAndReference(productToSave.getName(), productToSave.getReference());

        if(productNameReference.isPresent()){
            throw new ExceptionRegister("Product already exist with name and reference");
        }
    }
}
