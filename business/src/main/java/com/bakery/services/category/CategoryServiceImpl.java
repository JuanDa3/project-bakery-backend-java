package com.bakery.services.category;

import com.bakery.entities.Category;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionFind;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category registerCategory(Category category) throws ExceptionRegister {
        validateNull(category);
        validateRepeated(category);
        validateEmpty(category);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) throws ExceptionDelete {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> listCategories() throws ExceptionList {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) throws ExceptionFind {
        if(categoryRepository.findById(id).isEmpty()){
            throw new ExceptionFind("Category not exist");
        }
        return categoryRepository.findById(id);
    }

    private void validateNull(Category category){
        if (category == null){
            throw new ExceptionRegister("Category can't be null");
        }
    }

    private void validateRepeated(Category category){
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new ExceptionRegister("Category is already registered");
        }
    }

    private void validateEmpty(Category category){
        if(category.getName().equals("")){
            throw new ExceptionRegister("Category name can't be empty");
        }
    }
}
