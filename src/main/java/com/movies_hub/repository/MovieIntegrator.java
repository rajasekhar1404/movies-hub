package com.movies_hub.repository;

import com.movies_hub.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@AllArgsConstructor
public class MovieIntegrator {

    private final MovieRepository movieRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<Movie> save(Movie movie) {
        movie.setDocumentId(UUID.randomUUID().toString());
        return movieRepository.save(movie);
    }

    public Flux<Movie> findByQuery(Query query) {
        return reactiveMongoTemplate.query(Movie.class)
                .matching(query)
                .all();
    }

}
