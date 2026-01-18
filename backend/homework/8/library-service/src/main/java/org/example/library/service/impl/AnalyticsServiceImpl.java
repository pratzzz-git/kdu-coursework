package org.example.library.service.impl;
import org.example.library.domain.repository.BookRepository;
import org.example.library.service.AnalyticsService;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final BookRepository bookRepository;

    public AnalyticsServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Map<String, Long> countBooksByStatus() {
        return bookRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        book -> book.getStatus().name(),
                        Collectors.counting()
                ));
    }
}
