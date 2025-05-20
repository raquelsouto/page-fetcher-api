package com.page_fetcher_api.controller;

import com.page_fetcher_api.model.WebCrawler;
import com.page_fetcher_api.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebCrawlerController {

    @Autowired
    private WebCrawlerService webCrawlerService;

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
