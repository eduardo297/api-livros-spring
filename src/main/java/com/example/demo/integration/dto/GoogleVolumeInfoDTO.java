package com.example.demo.integration.dto;

import java.util.List;

public class GoogleVolumeInfoDTO {

    private String title;
    private List<String> authors;
    private List<GoogleIndustryIdentifierDTO> industryIdentifiers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<GoogleIndustryIdentifierDTO> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<GoogleIndustryIdentifierDTO> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }
}