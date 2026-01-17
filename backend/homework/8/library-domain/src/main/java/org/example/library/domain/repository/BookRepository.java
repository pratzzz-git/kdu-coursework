package org.example.library.domain.repository;

import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Page<Book> findByStatus(BookStatus status, Pageable pageable);

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByStatusAndTitleContainingIgnoreCase(
            BookStatus status,
            String title,
            Pageable pageable
    );
}
