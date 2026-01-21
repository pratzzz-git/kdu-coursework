package com.cinestream.controller;

import com.cinestream.dto.ReviewDto;
import com.cinestream.entity.DirectorEntity;
import com.cinestream.entity.MovieEntity;
import com.cinestream.service.DirectorService;
import com.cinestream.service.MovieService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final DirectorService directorService;

    public MovieController(
            MovieService movieService,
            DirectorService directorService
    ) {
        this.movieService = movieService;
        this.directorService = directorService;
    }

    // -------- Exercise 1 & 2 --------

    @QueryMapping
    public MovieEntity findMovieById(@Argument Long id) {
        return movieService.findMovieById(id);
    }

    @SchemaMapping(typeName = "Movie", field = "director")
    public DirectorEntity director(MovieEntity movie) {
        return directorService.findById(movie.getDirectorId());
    }

    // -------- Exercise 3 --------

    @MutationMapping
    public MovieEntity addReview(
            @Argument Long movieId,
            @Argument String comment,
            @Argument int rating
    ) {
        return movieService.addReview(movieId, comment, rating);
    }

    @SchemaMapping(typeName = "Movie", field = "reviews")
    public List<ReviewDto> reviews(MovieEntity movie) {
        return movieService.getReviews(movie.getId());
    }
}
