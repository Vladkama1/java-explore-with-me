package ru.practicum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.exception.ClientsException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class StatsClients {

    private final RestTemplate restTemplate;

    private static final String API_HIT = "/hit";
    private static final String API_STATS = "/stats?start=%s&end=%s&uris=%s&unique=%s";


    public StatsClients(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();
    }

    public void postStats(StatDTO statDTO) {
        if (statDTO == null) {
            throw new IllegalArgumentException("Stats input data cannot be null");
        }
        try {
            restTemplate.postForLocation(API_HIT, statDTO);
        } catch (HttpStatusCodeException e) {
            throw ClientsException.builder().message(e.getMessage()).build();
        }
    }

    public List<StatOutDTO> getStats(String start, String end, List<String> uris, boolean unique) {
        String url = String.format(API_STATS, start, end, uris, unique);
        try {
            ResponseEntity<StatOutDTO[]> response = restTemplate.getForEntity(url, StatOutDTO[].class);

            return (response.getBody() != null) ? Arrays.asList(response.getBody()) : Collections.emptyList();
        } catch (HttpStatusCodeException e) {
            throw ClientsException.builder().message(e.getMessage()).build();
        }
    }
}
