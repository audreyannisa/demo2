package com.example.demo2.controller;

import com.example.demo2.entity.Book;
import com.example.demo2.entity.Category;
import com.example.demo2.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        logger.info("Saving category {}", category.getCategoryName());
        return categoryService.save(category);
    }

    @GetMapping
    public List<Category> getAll() {
        logger.info("Getting all books...");
        return categoryService.getAll();
    }


}
