package com.sag.bookManagementSystem.controller;

import com.sag.bookManagementSystem.constants.Category;
import com.sag.bookManagementSystem.dto.Book;
import com.sag.bookManagementSystem.exceptions.BookNotFoundException;
import com.sag.bookManagementSystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the bookstore projects
 * 1)Add a book
 * 2)get books by Id
 * 3)get all books
 * 4)get number of books available by Id
 * 5)update a book
 * 6)get books by category/keywords
 * 7) Delete Book by Id
 */

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 1)Add a new book
     */
    @PostMapping("/add-new-book")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewBook(@Valid @RequestBody Book book) {
        bookService.addNewBook(book);
    }

    /**
     * 1)Add quantity of book to the books which are already registered.
     */
    @PutMapping("/add-book/{id}/{quantityToAdd}")
    @ResponseStatus(HttpStatus.OK)
    public void addBook(@PathVariable Long id,
                        @PathVariable int quantityToAdd) {
        bookService.addBook(id, quantityToAdd);
    }

    /**
     * 2)get books by id
     */
    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }


    /**
     * 3)Get All Books
     */
    @GetMapping("/book-list")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * 4) Get number of books available by id.
     */
    @GetMapping("/number-of-books/{id}")
    public int getNumberOfBooksById(@PathVariable Long id) {
        return bookService.getNumberOfBooksById(id);
    }

    /**
     * 5) Update a book.
     */
    @PutMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id,
                           @Valid @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    /**
     * 6) Get Books by category/keyword
     */
    @GetMapping("/books")
    public List<Book> getBookByCategoryKeyWord(@RequestParam String keyword,
                                               @RequestParam Category category) {
        return bookService.getBookByCategoryKeyWord(keyword, category);
    }

    /**
     * 7) Delete Book by Id
     */
    @DeleteMapping("/delete-book/{id}")
    public String deleteBookById(@PathVariable Long id) {
        boolean check = bookService.deleteBookById(id);
        if (check)
            return "Book deleted successfully";
        else
            throw new BookNotFoundException("Book with ID " + id + " not found");
    }
}
