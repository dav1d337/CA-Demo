package com.koch.sampleproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    private String originalTitle;

    @JsonCreator
    public Movie(@JsonProperty("original_title") String originalTitle, @JsonProperty("original_name") String originalName) {
        this.originalTitle = originalTitle;
        if (originalTitle == null) {
            this.originalTitle = originalName;
        }
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
}
