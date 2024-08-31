package com.movies_hub.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private String message;
    private String code;

}
