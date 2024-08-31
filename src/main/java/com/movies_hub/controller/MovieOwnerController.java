package com.movies_hub.controller;

import com.movies_hub.model.Movie;
import com.movies_hub.model.Response;
import com.movies_hub.service.MovieUploadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/v2/upload")
public class MovieOwnerController {

    private final MovieUploadService movieUploadService;

    @PostMapping("/movie")
    public Mono<Response> uploadMovie(@RequestBody Movie movie) {
        return movieUploadService.uploadMovie(movie);
    }

}
