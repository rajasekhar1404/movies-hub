package com.movies_hub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BaseModel {

    @Id
    @JsonIgnore
    String documentId;

}
