package com.bakery.apirest;

import com.bakery.entities.Product;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.exceptions.ExceptionUpdate;
import com.bakery.factory.DtoProduct;
import com.bakery.services.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>createProduct(@RequestBody Product product){
        System.out.println(product);
        Product newProduct;
        Map<String, Object> response = new HashMap<>();
        try {
            newProduct = productService.registerProduct(product);
        } catch (ExceptionRegister e) {
            response.put("message","Error registering product");
            response.put("error", e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "product registered correctly");
        response.put("product",newProduct);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>showProductById(@PathVariable Integer id){
        Optional<Product>product;
        Map<String, Object> response = new HashMap<>();

        try {
            product = productService.findProductById(id);
        } catch (RuntimeException e) {
            response.put("message","Error in product");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("product",product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteProduct(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();

        try {
            productService.deleteProduct(id);
        }catch (RuntimeException e){
            response.put("message","Error in product");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "product deleted correctly");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?>listProducts(){
        List<DtoProduct> productList = productService.listProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateProduct(@RequestBody Product product, @PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        Product productUpdated;
        try {
            productUpdated = productService.updateProduct(id, product);
        } catch (ExceptionUpdate e) {
            response.put("message","Error in product");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message","product updated correctly");
        response.put("product",productUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
