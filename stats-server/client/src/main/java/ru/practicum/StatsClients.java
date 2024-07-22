package ru.practicum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class StatsClients {
    private final RestTemplate restTemplate;
    private static final String API_HIT = "/hit";
    private static final String API_STATS = "/stats?start=%s&end=%s%s&unique=%s";


    public StatsClients(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();
    }

    public void postStats(String app, String uri, String ip, LocalDateTime timestamp) {

        final EndpointHit endpointHit = new EndpointHit(app, uri, ip, timestamp);

        restTemplate.postForLocation(API_HIT, endpointHit);
    }

    public List<ViewStats> getStats(String start, String end, List<String> uris, Boolean unique) {

        String urisParam = uris.isEmpty() ? "" : "&uris=" + String.join(",", uris);
        String url = String.format(API_STATS, start, end, urisParam, unique);

        ResponseEntity<ViewStats[]> response = restTemplate.getForEntity(url, ViewStats[].class);

        return (response.getBody() != null) ? Arrays.asList(response.getBody()) : Collections.emptyList();
    }
}
