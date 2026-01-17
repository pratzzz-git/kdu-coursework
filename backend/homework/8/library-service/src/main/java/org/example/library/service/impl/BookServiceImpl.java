package org.example.library.service.impl;

import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.example.library.domain.repository.BookRepository;
import org.example.library.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setStatus(BookStatus.PROCESSING);
        return bookRepository.save(book);
    }

    @Override
    public Book catalogBook(UUID bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book not found"));

        if (book.getStatus() != BookStatus.PROCESSING) {
            throw new IllegalStateException("Invalid state transition");
        }

        book.setStatus(BookStatus.AVAILABLE);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
