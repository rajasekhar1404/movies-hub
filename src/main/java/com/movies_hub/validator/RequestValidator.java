package com.movies_hub.validator;

import com.movies_hub.exception.BadRequestException;
import com.movies_hub.model.Movie;
import com.movies_hub.model.Person;
import com.movies_hub.model.RequestMovies;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestValidator {

    String errorMessage = "Required field %s is missing";

    public Movie validate(Movie movie) {

        List<String> errors = new ArrayList<>();

        if (StringUtils.isEmpty(movie.getTitle())) {
            errors.add(String.format(errorMessage, "title"));
        }
        if (StringUtils.isEmpty(movie.getReleaseDate())) {
            errors.add(String.format(errorMessage, "release date"));
        }
        if (StringUtils.isEmpty(movie.getStoryLine())) {
            errors.add(String.format(errorMessage, "story line"));
        }
        if (CollectionUtils.isEmpty(movie.getCast())) {
            errors.add(String.format(errorMessage, "cast"));
        } else {
            movie.getCast().forEach(person -> validateCast(person, errors));
        }

        if (CollectionUtils.isEmpty(movie.getGenre())) {
            errors.add(String.format(errorMessage, "genre"));
        }
        if (CollectionUtils.isNotEmpty(errors)) throw new BadRequestException(String.join(",", errors));

        return movie;

    }

    private void validateCast(Person person, List<String> errors) {

        List<String> personErrors = new ArrayList<>();

        if (StringUtils.isEmpty(person.getFirstName())) {
            personErrors.add(String.format(errorMessage, "first name"));
        }
        if (StringUtils.isEmpty(person.getLastName())) {
            personErrors.add(String.format(errorMessage, "last name"));
        }
        if (StringUtils.isEmpty(person.getRole())) {
            personErrors.add(String.format(errorMessage, "role"));
        }

        errors.addAll(personErrors);

    }

    public RequestMovies validate(RequestMovies request) {
        return request;
    }

}
