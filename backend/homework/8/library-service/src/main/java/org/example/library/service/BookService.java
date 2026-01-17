package org.example.library.service;

import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService {

    Book createBook(String title);

    Book catalogBook(UUID bookId);

    List<Book> getAllBooks();

    Page<Book> getBooks(BookStatus status, String titleContains, Pageable pageable);
}
