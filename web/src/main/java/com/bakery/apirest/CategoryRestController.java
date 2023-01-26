package com.bakery.apirest;

import com.bakery.entities.Category;
import com.bakery.services.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>createCategory(@RequestBody Category category){
        Category newCategory;
        Map<String, Object> response = new HashMap<>();
        try {
            newCategory = categoryService.registerCategory(category);
        } catch (RuntimeException e) {
            response.put("message","Error registering category");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "category registered correctly");
        response.put("category",newCategory.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>showCategoryById(@PathVariable Integer id){
        Optional<Category> category;
        Map<String, Object> response = new HashMap<>();

        try {
            category = categoryService.findCategoryById(id);
        } catch (RuntimeException e) {
            response.put("message","Error in category");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "category registered correctly");
        response.put("category",category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteCategory(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();

        try {
            categoryService.deleteCategory(id);
        }catch (RuntimeException e){
            response.put("message","Error in category");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "category deleted correctly");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
