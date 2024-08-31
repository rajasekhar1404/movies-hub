package com.movies_hub.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestMovies {

    private String title;
    private String actor;
    private String releaseDate;
    private List<String> genre;
    private List<String> language;

}
