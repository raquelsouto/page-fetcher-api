package com.page_fetcher_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrawlerData {
    private String domain;
    private List<String> pages;
    private List<String> images;
}
