package org.example.library.web.mapper;

import org.example.library.api.dto.response.BookResponse;
import org.example.library.domain.entity.Book;

public class BookMapper {

    public static BookResponse toResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setStatus(book.getStatus().name());
        response.setCreatedAt(book.getCreatedAt());
        return response;
    }
}
