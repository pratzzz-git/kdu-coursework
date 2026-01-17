package org.example.library.web.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;

import org.example.library.api.dto.request.CreateBookRequest;
import org.example.library.api.dto.response.BookResponse;
import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.example.library.domain.repository.BookRepository;
import org.example.library.service.BookService;
import org.example.library.web.mapper.BookMapper;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.example.library.domain.enums.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "Books", description = "Book management APIs")
@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Create a new book")
    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody CreateBookRequest request) {


        log.info("POST /books called with title={}", request.getTitle());

        Book book = bookService.createBook(request.getTitle());

        log.info("Book created successfully with id={}", book.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BookMapper.toResponse(book));
    }


    @Operation(summary = "Get books with pagination and filtering")
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getBooks(
            @RequestParam(name = "status", required = false) BookStatus status,
            @RequestParam(name = "titleContains", required = false) String titleContains,
            @PageableDefault(sort = "createdAt")
            @ParameterObject Pageable pageable
    ) {
        log.info(
                "GET /books called | status={} | titleContains={} | page={} | size={}",
                status,
                titleContains,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<BookResponse> response =
                bookService.getBooks(status, titleContains, pageable)
                        .map(BookMapper::toResponse);

        log.info("GET /books returning {} records", response.getNumberOfElements());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<List<BookResponse>> testBooks() {
        return ResponseEntity.ok(
                bookService.getAllBooks()
                        .stream()
                        .map(BookMapper::toResponse)
                        .toList()
        );
    }


}
