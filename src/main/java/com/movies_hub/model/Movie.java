package com.movies_hub.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "${spring.data.mongodb.collection}")
public class Movie extends BaseModel {

    private String title;
    private List<Person> cast;
    private List<String> genre;
    private String storyLine;
    private String releaseDate;
    private List<String> language;

}
