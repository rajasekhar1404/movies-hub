package com.movies_hub.enricher;

import com.movies_hub.model.Movie;
import com.movies_hub.model.RequestMovies;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieEnricher {

    public Movie enrich(Movie movie) {

        var enrichedMovie = Movie.builder();

        if (StringUtils.isNotEmpty(movie.getStoryLine())) {
            enrichedMovie.storyLine(tranformString(movie.getStoryLine()));
        }
        if (StringUtils.isNotEmpty(movie.getTitle())) {
            enrichedMovie.title(movie.getTitle());
        }
        if (StringUtils.isNotEmpty(movie.getReleaseDate())) {
            enrichedMovie.releaseDate(tranformString(movie.getReleaseDate()));
        }
        if (CollectionUtils.isNotEmpty(movie.getGenre())) {
            enrichedMovie.genre(transformList(movie.getGenre()));
        }
        if (CollectionUtils.isNotEmpty(movie.getLanguage())) {
            enrichedMovie.language(transformList(movie.getLanguage()));
        }
        if (CollectionUtils.isNotEmpty(movie.getCast())) {
            enrichedMovie.cast(movie.getCast());
        }

        return enrichedMovie.build();

    }

    public RequestMovies enrich(RequestMovies requestMovies) {
        var request = RequestMovies.builder();

        if (StringUtils.isNotEmpty(requestMovies.getTitle()))
            request.title(requestMovies.getTitle().trim());
        if (StringUtils.isNotEmpty(requestMovies.getReleaseDate()))
            request.releaseDate(tranformString(requestMovies.getReleaseDate()));
        if (StringUtils.isNotEmpty(requestMovies.getActor()))
            request.title(tranformString(requestMovies.getActor()));
        if (CollectionUtils.isNotEmpty(requestMovies.getLanguage()))
            request.language(transformList(requestMovies.getLanguage()));
        if (CollectionUtils.isNotEmpty(requestMovies.getGenre()))
            request.genre(transformList(requestMovies.getGenre()));

        return requestMovies;
    }

    private String tranformString(String string) {
        return string.toLowerCase().trim();
    }

    private List<String> transformList(List<String> list) {
        return list.stream().map(String::toLowerCase).map(String::trim).toList();
    }

}
