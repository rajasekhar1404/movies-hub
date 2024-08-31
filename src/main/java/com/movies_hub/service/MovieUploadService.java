package com.movies_hub.service;

import com.movies_hub.enricher.MovieEnricher;
import com.movies_hub.exception.BadRequestException;
import com.movies_hub.model.Movie;
import com.movies_hub.model.Response;
import com.movies_hub.repository.MovieIntegrator;
import com.movies_hub.validator.RequestValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class MovieUploadService {

    private final MovieIntegrator movieIntegrator;
    private final MovieEnricher movieEnricher;
    private final RequestValidator validator;

    public Mono<Response> uploadMovie(Movie movie) {
        return Mono.just(movieEnricher.enrich(movie))
                .map(validator::validate)
                .flatMap(movieIntegrator::save)
                .doOnSuccess(res -> log.info("Saved movie successfully: {}", res))
                .map(res -> Response.builder().code("200").message(String.format("Successfully saved %s", res.getTitle())).build())
                .onErrorResume(this::handleException);
    }

    private Mono<? extends Response> handleException(Throwable throwable) {

        if (throwable instanceof BadRequestException) {
            log.error("Exception occurred while saving movie with exception: {}", throwable.getMessage());
            return Mono.just(Response.builder().message(String.format("Failed while saving the movie due to: %s", throwable.getMessage())).code("400").build());
        }
        return Mono.just(Response.builder().message(String.format("Failed while saving the movie due to: %s", throwable.getMessage())).code("500").build());

    }


}
