package org.example.library.service;

import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {
    Book createBook(String title);
    Book catalogBook(UUID id);
    Page<Book> getBooks(BookStatus status, String title, Pageable pageable);
}
