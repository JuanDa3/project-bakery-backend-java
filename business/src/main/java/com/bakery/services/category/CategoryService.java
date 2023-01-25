package com.bakery.services.category;

import com.bakery.entities.Category;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;

import java.util.List;

public interface CategoryService {

    String registerCategory(Category category) throws ExceptionRegister;

    String deleteCategory(Category category) throws ExceptionDelete;

    List<Category>listCategories() throws ExceptionList;
}
