package com.page_fetcher_api.service;

import com.page_fetcher_api.model.CrawlerData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;


@Service
public class WebCrawlerService {

    public CrawlerData crawlTarget(String target) throws Exception {
        URI targetUri = new URI(target);
        String domain = extractDomain(targetUri);

        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();

        //images
        Set<String> foundImages = new HashSet<>();

        queue.add(normalizeUrl(domain, targetUri.getPath()));

        while(!queue.isEmpty()) {
            String newUrl = queue.poll();

            if(!visited.add(newUrl)) {
                continue;
            }

            try {
                Document document = Jsoup.connect(newUrl).get();
                Elements links = document.select("a[href]");
                for (Element link : links) {
                    String absHref = link.attr("abs:href").split("#")[0];
//                    System.out.println(absHref);
                    if (absHref.startsWith(domain) && !visited.contains(absHref) && !queue.contains(absHref)) {
                        queue.add(absHref);
                    }
                }

                Elements images = document.select("img[src]");
                for (Element image : images) {
                    String imgUrl = image.attr("abs:src");
//                    System.out.println("IMAGE: " + imgUrl);

                    if (!foundImages.contains(imgUrl)) {
                        foundImages.add(imgUrl);
                    }
                }
            } catch (Exception e) {
                // Ignore errors for unreachable pages
            }
        }

        return new CrawlerData(domain, new ArrayList<>(visited), new ArrayList<>(foundImages));
    }

    //extracts the base domain from the URI
    private String extractDomain(URI uriRecived) {
        return uriRecived.getScheme() + "://" + uriRecived.getHost();

    }

    //normalizes URL. adds "/" if path is empty
    private String normalizeUrl(String domain, String pathUrl) {
        return domain + (pathUrl == null || pathUrl.isEmpty() ? "/" : pathUrl);
    }
}
