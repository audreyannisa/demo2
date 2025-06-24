package com.example.demo2.service;

import com.example.demo2.entity.Book;
import com.example.demo2.entity.Category;
import com.example.demo2.repository.BookRepository;
import com.example.demo2.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCategoryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

//    @Transactional
//    public Book saveBookAndCategory(String title, String nameCategory, boolean throwError) {
//        Optional<Category> category = categoryRepository.findCategoryByCategoryName(nameCategory);
//
//
//        if (category == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
//        }
//
//
//        Book book = new Book();
//        book.setTitle(title);
//        book.setAuthor("Author " + title);
//        book.setYearPublished(2023);
//        book.setCategory(category);
//
//        Book savedBook = bookRepository.save(book);
//
//        if (throwError) {
//            throw new RuntimeException("Simulate rollback");
//        } else {
//            return savedBook;
//        }
//    }

    @Transactional
    public Book saveBookWithCategories(String title, List<String> categoryNames) {
        List<Category> categories = categoryNames.stream()
                .map(name -> categoryRepository.findCategoryByCategoryName(name)
                        .orElseGet(() -> categoryRepository.save(new Category(null, name, null, new ArrayList<>())))
                ).collect(Collectors.toList());

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor("Author " + title);
        book.setYearPublished(2023);
        book.setCategories(categories);

        return bookRepository.save(book);
    }
}
