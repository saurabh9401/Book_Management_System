package com.sag.bookManagementSystem.service;

import com.sag.bookManagementSystem.constants.Category;
import com.sag.bookManagementSystem.dto.Book;
import com.sag.bookManagementSystem.exceptions.BadRequestException;
import com.sag.bookManagementSystem.exceptions.BookNotFoundException;
import com.sag.bookManagementSystem.exceptions.DuplicateResourceException;
import com.sag.bookManagementSystem.repo.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImplementation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * Register new book into database
     */
    @Override
    @Transactional
    public void addNewBook(Book book) {
        //Check if book is already present
        bookRepository.findById(book.getId()).ifPresent(b -> {
            throw new DuplicateResourceException("Book with same id present.\n " +
                    "Either use update methods to update the book or use addBook(Long id, int quantityToAdd) to add quantity");
        });
        bookRepository.save(book);
    }

    /**
     * This method adds the quantity of book if the book with given id is already registered.
     */
    @Override
    public void addBook(Long id, int quantityToAdd) {
        //Get the book by id
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id:" + id + " is not registered. Use addNewBook to register."));

        int totalCountAfterAdd = book.getTotalCount() + quantityToAdd;
        book.setTotalCount(totalCountAfterAdd);

        bookRepository.save(book);
    }

    /**
     * Get book by id
     */
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id:" + id + " is not found."));
    }


    /**
     * List all the books
     */
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Number of books on particular identifier
     */
    @Override
    public int getNumberOfBooksById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        //If book is present get Total Count else return 0
        return book.map(Book::getTotalCount).orElse(0);
    }

    /**
     * update a book
     */
    @Override
    @Transactional
    public void updateBook(Long id, Book book) {
        if (book.getId() != null) {
            if (!book.getId().equals(id)) {
                throw new BadRequestException("Id cannot be updated.");
            }
        }
        book.setId(id);
        bookRepository.save(book);
    }


    /**
     * Get the list of books by category and keyword
     */
    @Override
    public List<Book> getBookByCategoryKeyWord(String keyword,
                                               Category category) {
        return bookRepository.findAllBookByCategoryAndKeyword(keyword.toLowerCase(), category.getValue());
    }

    /**
     * Delete an existing book by using Id
     */
    @Override
    public boolean deleteBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Reduce Quantity of Books
     */
    @Override
    public void reduceBook(Long id, int quantityToReduce) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id:" + id + " is not registered. Use addNewBook to register."));

        int totalCountAfterReduce = book.getTotalCount() - quantityToReduce;
        book.setTotalCount(totalCountAfterReduce);

        bookRepository.save(book);
    }
}
