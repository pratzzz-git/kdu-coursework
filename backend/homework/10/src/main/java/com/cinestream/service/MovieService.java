package com.cinestream.service;

import com.cinestream.dto.ReviewDto;
import com.cinestream.entity.MovieEntity;
import com.cinestream.repository.MovieJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    private final MovieJpaRepository movieRepository;

    // In-memory store for reviews (Exercise 3)
    private final Map<Long, List<ReviewDto>> reviewsByMovie = new HashMap<>();

    public MovieService(MovieJpaRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // -------- Exercise 1 & 2 --------

    public MovieEntity findMovieById(Long id) {
        log.info("Fetching movie with id={}", id);
        return movieRepository.findById(id).orElse(null);
    }

    // -------- Exercise 3 --------

    public MovieEntity addReview(Long movieId, String comment, int rating) {
        log.info("Adding review for movieId={}, rating={}", movieId, rating);

        reviewsByMovie
                .computeIfAbsent(movieId, k -> new ArrayList<>())
                .add(new ReviewDto(comment, rating));

        return findMovieById(movieId);
    }

    public List<ReviewDto> getReviews(Long movieId) {
        return reviewsByMovie.getOrDefault(movieId, List.of());
    }
}
