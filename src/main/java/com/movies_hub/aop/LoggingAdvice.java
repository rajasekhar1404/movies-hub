package com.movies_hub.aop;

import com.movies_hub.model.Movie;
import com.movies_hub.model.RequestMovies;
import com.movies_hub.utils.MoviesHubUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Aspect
@Component
public class LoggingAdvice {

    @Pointcut("execution(* com.movies_hub.controller.MovieController.*(..))")
    public void logOnMovieController() {}

    @Pointcut("execution(* com.movies_hub.controller.MovieOwnerController.*(..))")
    public void logOnMovieOwnerController() {}

    @Around("logOnMovieController() && args(requestMovies)")
    public Flux<Object> logIncomingRequests(ProceedingJoinPoint pjp, RequestMovies requestMovies) throws Throwable {
        var requestString = MoviesHubUtils.getObjectAsString(requestMovies);
        log.info("Request received to microservice: {}", requestString);
        return ((Flux<Object>) pjp.proceed())
                .doOnNext(res -> log.info("Returning from microservice for request: {}", requestString));
    }

    @Around("logOnMovieOwnerController() && args(movie)")
    public Mono<Object> logIncomingOwnerRequests(ProceedingJoinPoint pjp, Movie movie) throws Throwable {
        var requestString = MoviesHubUtils.getObjectAsString(movie);
        log.info("Request received to microservice for uploading a movie: {}", requestString);
        return ((Mono<Object>) pjp.proceed())
                .doOnNext(res -> log.info("Returning from microservice for request: {}", requestString));
    }

}
