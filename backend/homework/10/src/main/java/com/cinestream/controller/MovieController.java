package com.cinestream.controller;

import com.cinestream.entity.DirectorEntity;
import com.cinestream.entity.MovieEntity;
import com.cinestream.service.DirectorService;
import com.cinestream.service.MovieService;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

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

    @SchemaMapping(typeName = "Movie", field = "director")
    public DirectorEntity director(MovieEntity movie) {
        return directorService.findById(movie.getDirectorId());
    }
}
