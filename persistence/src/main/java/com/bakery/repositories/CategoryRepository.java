package com.bakery.repositories;

import com.bakery.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.name = :categoryName")
    Optional<Category> findByName(String categoryName);

    @Query("select c from Category c")
    Page<Category> listAllPage(Pageable pageable);
}
