package com.movies_hub.controller;

import com.movies_hub.model.Movie;
import com.movies_hub.model.RequestMovies;
import com.movies_hub.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/query")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/movie")
    public Flux<Movie> getMovies(@RequestBody RequestMovies requestMovies) {
        return movieService.getMovies(requestMovies);
    }

}
