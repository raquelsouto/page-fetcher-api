package com.page_fetcher_api.controller;

import com.page_fetcher_api.model.WebCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class WebCrawlerController {

    private final String URL = "https://filip-ph-johansson.github.io";

    private WebCrawler pages = new WebCrawler();

    @GetMapping("/pages")
    public ResponseEntity<?> getPagesResults(@RequestParam String target) {
        pages.setDomain(URL);
        pages.setPages(new ArrayList<>(Arrays.asList(
                URL,
                URL+ "/contact.html",
                URL+ "/about.html"
        )));
        return ResponseEntity.ok().body(pages);
    }
}
