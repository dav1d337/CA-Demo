package com.koch.sampleproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieResponse {

    private int page;
    private List<Movie> results;
    private int totalPages;
    private int totalResults;

    @JsonCreator
    public MovieResponse(@JsonProperty("page")int page, @JsonProperty("results") List<Movie> results, @JsonProperty("total_pages") int totalPages, @JsonProperty("total_results") int totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
