package com.example.demo.students;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    private static final String API_URL = "https://api.mediastack.com/v1/news";
    private static final String API_KEY = "02132885fa35b1256c313732d6426348\n";
    private static final int PULL_INTERVAL_MINUTES = 10;

    private final RestTemplate restTemplate;
    private List<NewsRecord> newsRecords;

    public NewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NewsRecord> getFilteredNews(
            String source,
            LocalDateTime publishedDateStart,
            LocalDateTime publishedDateEnd,
            String titleContains,
            String country,
            String language,
            int page,
            int size
    ) {
        List<NewsRecord> filteredNews = newsRecords;


        if (source != null) {
            filteredNews = filterBySource(filteredNews, source);
        }

        if (publishedDateStart != null) {
            filteredNews = filterByPublishedDateStart(filteredNews, publishedDateStart);
        }

        if (publishedDateEnd != null) {
            filteredNews = filterByPublishedDateEnd(filteredNews, publishedDateEnd);
        }

        if (titleContains != null) {
            filteredNews = filterByTitleContains(filteredNews, titleContains);
        }

        if (country != null) {
            filteredNews = filterByCountry(filteredNews, country);
        }

        if (language != null) {
            filteredNews = filterByLanguage(filteredNews, language);
        }

        filteredNews = applyPagination(filteredNews, page, size);

        return filteredNews;
    }

    private List<NewsRecord> filterBySource(List<NewsRecord> news, String source) {

        return news;
    }

    private List<NewsRecord> filterByPublishedDateStart(List<NewsRecord> news, LocalDateTime publishedDateStart) {

        return news;
    }

    private List<NewsRecord> filterByPublishedDateEnd(List<NewsRecord> news, LocalDateTime publishedDateEnd) {

        return news;
    }

    private List<NewsRecord> filterByTitleContains(List<NewsRecord> news, String titleContains) {

        return news;
    }

    private List<NewsRecord> filterByCountry(List<NewsRecord> news, String country) {

        return news;
    }

    private List<NewsRecord> filterByLanguage(List<NewsRecord> news, String language) {

        return news;
    }

    private List<NewsRecord> applyPagination(List<NewsRecord> news, int page, int size) {
        if (news == null) {
            return new ArrayList<>();
        }

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, news.size());
        return news.subList(startIndex, endIndex);
    }

    @Scheduled(fixedDelay = PULL_INTERVAL_MINUTES * 60 * 1000) // Schedule pulling every 10 minutes
    private void pullNewsRecords() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("access_key", API_KEY);

        Map<String, String> urlParams = new HashMap<>();
        String url = uriBuilder.buildAndExpand(urlParams).toUriString();

        NewsResponse response = restTemplate.getForObject(url, NewsResponse.class);
        if (response != null) {
            newsRecords = response.getData();
        } else {
            newsRecords = new ArrayList<>();
        }
    }
}