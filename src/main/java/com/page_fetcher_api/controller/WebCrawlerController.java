package com.page_fetcher_api.controller;

import com.page_fetcher_api.model.WebCrawler;
import com.page_fetcher_api.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class WebCrawlerController {

    private final String URL = "https://filip-ph-johansson.github.io";

    @Autowired
    private WebCrawlerService webCrawlerService;

    private WebCrawler pages = new WebCrawler();

    @GetMapping("/pages")
    public ResponseEntity<?> getPagesResults(@RequestParam String target) throws Exception {
        try {
            WebCrawler result = webCrawlerService.crawlTarget(target);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid URL or error during crawling.");
        }
    }
}
