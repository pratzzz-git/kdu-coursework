package org.example.library.service.impl;
import lombok.extern.slf4j.Slf4j;
import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.example.library.domain.repository.BookRepository;
import org.example.library.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(String title) {
        log.info("Service: createBook | title={}", title);

        Book book = new Book();
        book.setTitle(title);
        book.setStatus(BookStatus.PROCESSING);

        return bookRepository.save(book);
    }

    @Override
    public Book catalogBook(UUID bookId) {
        log.info("Service: catalogBook | bookId={}", bookId);

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
    public Page<Book> getBooks(
            BookStatus status,
            String titleContains,
            Pageable pageable
    ) {
        log.info("Service: getBooks | status={} titleContains={}", status, titleContains);

        Page<Book> page = bookRepository.findAll(pageable);

        return page.map(book -> book); // filtering already safe at controller level
    }
}
