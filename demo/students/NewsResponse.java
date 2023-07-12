package com.example.demo.students;

import java.util.List;

public class NewsResponse {
    private List<NewsRecord> data;

    public List<NewsRecord> getData() {
        return data;
    }

    public void setData(List<NewsRecord> data) {
        this.data = data;
    }
}
