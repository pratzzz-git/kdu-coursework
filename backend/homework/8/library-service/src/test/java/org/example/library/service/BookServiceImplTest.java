package org.example.library.service;

import org.example.library.domain.entity.Book;
import org.example.library.domain.enums.BookStatus;
import org.example.library.domain.repository.BookRepository;
import org.example.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void shouldReturnPagedBooks() {
        Book book = new Book();
        book.setTitle("Clean Code");
        book.setStatus(BookStatus.PROCESSING);

        Page<Book> page =
                new PageImpl<>(List.of(book));

        when(bookRepository.findAll(PageRequest.of(0, 1)))
                .thenReturn(page);

        Page<Book> result =
                bookService.getBooks(null, null, PageRequest.of(0, 1));

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getTitle())
                .isEqualTo("Clean Code");
    }
}
