package org.example.library.service;

import org.example.library.domain.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {

    Book createBook(String title);

    Book catalogBook(UUID bookId);

    List<Book> getAllBooks();
}
