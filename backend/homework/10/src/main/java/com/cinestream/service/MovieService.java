package com.cinestream.service;

import com.cinestream.dto.MovieDto;
import com.cinestream.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieDto findMovieById(String id) {
        log.info("Fetching movie with id={}", id);
        return repository.findById(id).orElse(null);
    }
}
