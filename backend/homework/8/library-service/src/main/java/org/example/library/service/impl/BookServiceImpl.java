package org.example.library.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.example.library.domain.repository.BookRepository;
import org.example.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(String title) {
        log.info("Service: createBook started | title={}", title);

        Book book = new Book();
        book.setTitle(title);
        book.setStatus(BookStatus.PROCESSING);

        Book saved = bookRepository.save(book);

        log.info(
                "Service: createBook completed | id={} | status={}",
                saved.getId(),
                saved.getStatus()
        );

        return saved;
    }

    @Override
    public Book catalogBook(UUID bookId) {
        log.info("Service: catalogBook started | bookId={}", bookId);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.warn("Service: catalogBook failed | bookId={} not found", bookId);
                    return new IllegalStateException("Book not found");
                });

        if (book.getStatus() != BookStatus.PROCESSING) {
            log.warn(
                    "Service: invalid state transition | bookId={} | currentStatus={}",
                    bookId,
                    book.getStatus()
            );
            throw new IllegalStateException("Invalid state transition");
        }

        book.setStatus(BookStatus.AVAILABLE);

        log.info(
                "Service: catalogBook completed | bookId={} | newStatus={}",
                bookId,
                book.getStatus()
        );

        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        log.info("Service: getAllBooks called");

        List<Book> books = bookRepository.findAll();

        log.info("Service: getAllBooks returning {} books", books.size());

        return books;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> getBooks(BookStatus status, String title, Pageable pageable) {

        log.info(
                "Service: getBooks called | status={} | titleContains={} | page={} | size={}",
                status,
                title,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<Book> page = bookRepository.findAll(pageable);

        log.info(
                "Service: fetched {} books from DB before filtering",
                page.getNumberOfElements()
        );

        List<Book> filtered = page.getContent()
                .stream()
                .filter(book ->
                        (status == null || book.getStatus() == status) &&
                                (title == null || title.isBlank()
                                        || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                )
                .toList();

        log.info(
                "Service: getBooks completed | returned {} books after filtering",
                filtered.size()
        );

        return new PageImpl<>(filtered, pageable, page.getTotalElements());
    }
}
