package com.example.demo2.repository;

import com.example.demo2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryByCategoryName(String categoryName);
}
