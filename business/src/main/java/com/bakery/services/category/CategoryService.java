package com.bakery.services.category;

import com.bakery.entities.Category;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionFind;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category registerCategory(Category category) throws ExceptionRegister;

    void deleteCategory(Integer id) throws ExceptionDelete;

    List<Category>listCategories() throws ExceptionList;

    Optional<Category> findCategoryById(Integer id) throws ExceptionFind;
}
