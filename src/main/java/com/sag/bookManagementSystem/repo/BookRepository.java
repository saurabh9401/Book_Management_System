package com.sag.bookManagementSystem.repo;


import com.sag.bookManagementSystem.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "Select * from book b where " +
            "(b.title like %?1% OR CAST(b.id as CHAR) like %?1% OR LOWER(b.author) like %?1%) " +
            "AND b.category=?2",
            nativeQuery = true)
    List<Book> findAllBookByCategoryAndKeyword(String keyword, int category);
}
