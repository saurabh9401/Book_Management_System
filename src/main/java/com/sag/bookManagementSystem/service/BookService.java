package com.sag.bookManagementSystem.service;

import com.sag.bookManagementSystem.constants.Category;
import com.sag.bookManagementSystem.dto.Book;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BookService {

    void addNewBook(Book book);

    void addBook(Long id, int quantityToAdd);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    int getNumberOfBooksById(Long id);

    void updateBook(Long id, Book book);

    List<Book> getBookByCategoryKeyWord(String keyword, Category category);

    boolean deleteBookById(@PathVariable Long id);

    void reduceBook(Long id, int quantityToReduce);
}
