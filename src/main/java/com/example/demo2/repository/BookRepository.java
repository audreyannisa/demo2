package com.example.demo2.repository;

import com.example.demo2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    //otomatis dapet fitur CRUD (save, findById, findAll, deleteById)
}
