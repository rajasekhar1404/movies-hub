package com.movies_hub.service;

import com.movies_hub.enricher.MongoEnricher;
import com.movies_hub.enricher.MovieEnricher;
import com.movies_hub.model.Movie;
import com.movies_hub.model.RequestMovies;
import com.movies_hub.repository.MovieIntegrator;
import com.movies_hub.validator.RequestValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class MovieService {

    private final MovieEnricher movieEnricher;
    private final MovieIntegrator movieIntegrator;
    private final RequestValidator requestValidator;
    private final MongoEnricher mongoEnricher;

    public Flux<Movie> getMovies(RequestMovies requestMovies) {
        return Mono.just(movieEnricher.enrich(requestMovies))
                .map(requestValidator::validate)
                .map(mongoEnricher::getQueryByRequest)
                .flatMapMany(movieIntegrator::findByQuery)
                .onErrorResume(this::handleException);
    }

    private Flux<? extends Movie> handleException(Throwable throwable) {
        log.error("EXCEPTION: {}", throwable.getMessage());
        return Flux.error(throwable);
    }

}
