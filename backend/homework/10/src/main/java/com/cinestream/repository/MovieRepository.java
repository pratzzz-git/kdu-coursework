package com.cinestream.repository;

import com.cinestream.dto.MovieDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

    private final List<MovieDto> movies = List.of(
            new MovieDto("1", "Inception", "Sci-Fi"),
            new MovieDto("2", "Interstellar", "Sci-Fi"),
            new MovieDto("3", "The Dark Knight", "Action")
    );

    public Optional<MovieDto> findById(String id) {
        return movies.stream()
                .filter(movie -> movie.id().equals(id))
                .findFirst();
    }
}
