package com.example.demo.students;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class NewsRecord {
    private String author;
    private String title;
    private String description;
    private String url;
    private String source;
    private String image;
    private String category;
    private String language;
    private String country;
    @JsonProperty("published_at")
    private LocalDateTime publishedAt;}


