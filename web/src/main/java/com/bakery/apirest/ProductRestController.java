package com.bakery.apirest;

import com.bakery.entities.Product;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.exceptions.ExceptionUpdate;
import com.bakery.services.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Map<String, Object> response = new HashMap<>();
        try {
            productService.registerProduct(product);
        } catch (ExceptionRegister e) {
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "product registered correctly");
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

    @GetMapping("/list-all")
    public ResponseEntity<?>listProducts(){
        List<Product> productList = productService.listAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/page/{page}")
    public Page<Product>productPageable(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        return productService.listProductPageable(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateProduct(@RequestBody Product product, @PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            productService.updateProduct(id, product);
        } catch (ExceptionUpdate e) {
            response.put("message","Error in product");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message","product updated correctly");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
