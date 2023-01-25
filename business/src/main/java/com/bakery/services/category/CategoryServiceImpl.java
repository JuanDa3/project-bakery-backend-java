package com.bakery.services.category;

import com.bakery.entities.Category;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String registerCategory(Category category) throws ExceptionRegister {
        categoryRepository.save(category);
        return "Category saved correctly";
    }

    @Override
    public String deleteCategory(Category category) throws ExceptionDelete {
        categoryRepository.delete(category);
        return "Category deleted";
    }

    @Override
    public List<Category> listCategories() throws ExceptionList {
        return categoryRepository.findAll();
    }
}
