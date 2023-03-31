package com.bakery.services.category;

import com.bakery.entities.Category;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionFind;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category registerCategory(Category category) throws ExceptionRegister;

    void deleteCategory(Integer id) throws ExceptionDelete;

    List<Category>listCategories() throws ExceptionList;

    Optional<Category> findCategoryById(Integer id) throws ExceptionFind;

    Page<Category>listCategoryPageable(Pageable pageable) throws ExceptionFind;
}
