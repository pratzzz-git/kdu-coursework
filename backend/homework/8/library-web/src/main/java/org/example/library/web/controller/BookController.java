package org.example.library.web.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.request.CreateBookRequest;
import org.example.library.api.dto.response.BookResponse;
import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.example.library.service.BookService;
import org.example.library.web.mapper.BookMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE BOOK
    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @RequestBody @Valid CreateBookRequest request
    ) {
        log.info("POST /books | title={}", request.getTitle());

        BookResponse response =
                BookMapper.toResponse(
                        bookService.createBook(request.getTitle())
                );

        return ResponseEntity.ok(response);
    }

    // CATALOG BOOK (PROCESSING -> AVAILABLE)
    @PostMapping("/{bookId}/catalog")
    public ResponseEntity<BookResponse> catalogBook(
            @PathVariable(name = "bookId") UUID bookId
    ) {

        log.info("POST /books/{}/catalog", bookId);

        BookResponse response =
                BookMapper.toResponse(
                        bookService.catalogBook(bookId)
                );

        return ResponseEntity.ok(response);
    }

    // GET BOOKS (PAGINATION + FILTERS)  ✅ 500 SAFE
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getBooks(
            @RequestParam(name = "status", required = false) BookStatus status,
            @RequestParam(name = "titleContains", required = false) String titleContains,
            @PageableDefault(sort = "createdAt")
            @ParameterObject Pageable pageable
    ) {
        log.info(
                "GET /books | status={} titleContains={} page={} size={}",
                status,
                titleContains,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<BookResponse> response =
                bookService.getBooks(status, titleContains, pageable)
                        .map(BookMapper::toResponse);

        return ResponseEntity.ok(response);
    }
}
