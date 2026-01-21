package com.cinestream.controller;

import com.cinestream.dto.MovieDto;
import com.cinestream.service.MovieService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @QueryMapping
    public MovieDto findMovieById(@Argument String id) {
        return service.findMovieById(id);
    }
}
