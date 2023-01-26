package com.bakery.apirest;

import com.bakery.entities.Product;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.services.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/product")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>createProduct(@RequestBody Product product){
        Product newProduct = null;
        Map<String, Object> response = null;
        try {
            newProduct = productService.registerProduct(product);
            response = new HashMap<>();
        } catch (ExceptionRegister e) {
            response.put("message","Error registering product");
            response.put("error", e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "product registered correctly");
        response.put("product",newProduct);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
