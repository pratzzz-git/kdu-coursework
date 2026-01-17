package org.example.library.web.controller;

import org.example.library.api.dto.request.CreateBookRequest;
import org.example.library.api.dto.response.BookResponse;
import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.example.library.domain.repository.BookRepository;
import org.example.library.web.mapper.BookMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody CreateBookRequest request
    ) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setStatus(BookStatus.PROCESSING);

        Book saved = bookRepository.save(book);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BookMapper.toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books =
                bookRepository.findAll()
                        .stream()
                        .map(BookMapper::toResponse)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(books);
    }
}
