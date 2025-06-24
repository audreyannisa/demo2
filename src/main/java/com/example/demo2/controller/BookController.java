package com.example.demo2.controller;

import com.example.demo2.entity.ApiResponse;
import com.example.demo2.entity.Book;
import com.example.demo2.entity.Category;
import com.example.demo2.repository.BookRepository;
import com.example.demo2.service.BookCategoryService;
import com.example.demo2.service.BookService;
import com.example.demo2.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookCategoryService bookCategoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@RequestBody Book book) {
        logger.info("Saving book {}", book.getTitle());

        try {
            Book savedBook =  bookService.save(book);
            ApiResponse<Book> response = new ApiResponse<>("Book saved successfully", savedBook);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Internal server error: " + ex.getMessage(), null));
        }
    }

    @GetMapping
    public List<Book> getAll() {
        logger.info("Getting all books...");
        return bookService.getAll();
    }

    @DeleteMapping
    public void deleteBook(@RequestParam Long id) {
        logger.info("Delete book");
        bookService.delete(id);
    }

//    @PostMapping("success")
//    public Book successfullySave(@RequestParam String title, @RequestParam String categoryName) {
//        logger.info("[SUCCESS] save book {}", title);
//        return bookCategoryService.saveBookAndCategory(title, categoryName, false);
//    }
//
//    @PostMapping("failed")
//    public void failedSave(@RequestParam String title, @RequestParam String categoryName) {
//        logger.info("[FAILED] save book {}", title);
//        bookCategoryService.saveBookAndCategory(title, categoryName, true);
//    }


    @PostMapping("/many-to-many")
    public ResponseEntity<ApiResponse<Book>> createManyToManyBook(
            @RequestParam String title,
            @RequestParam List<String> categories) {

        Book book = bookCategoryService.saveBookWithCategories(title, categories);
        return ResponseEntity.ok(new ApiResponse<>("Book with categories saved", book));
    }


}
