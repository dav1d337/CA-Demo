package com.koch.sampleproject.model;

import androidx.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    private String originalTitle;

    @JsonCreator
    public Movie(@Nullable @JsonProperty("original_title") String originalTitle, @Nullable @JsonProperty("original_name") String originalName) {
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
