package com.example.demo.students;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/api/v1/news")
    public List<NewsRecord> getFilteredNews(
            @RequestParam(required = false) String source,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime publishedDateStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime publishedDateEnd,
            @RequestParam(required = false) String titleContains,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String language,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return newsService.getFilteredNews(source, publishedDateStart, publishedDateEnd, titleContains, country, language, page, size);
    }
}
