package unb.esi.bigdataml.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import unb.esi.bigdataml.dto.ApiResponse;

import java.time.LocalDate;


@Service
public class CollecteService {

private final WebClient webClient;

    public CollecteService(WebClient webClient) {
        this.webClient = webClient;
    }

            @Value("${polygon.uri}")
        String uri;

        @Value("${polygon.apiKey}")
        String apiKey;

public ApiResponse collecte(LocalDate dateDebut)
{

    LocalDate date = dateDebut;

    try {
        ApiResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri+"{date}")
                        .queryParam("adjusted", "true")
                        .queryParam("apiKey", apiKey)
                        .build(date.toString()))
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .block();

       // System.out.println("Response: " + response);
        return response;

    } catch (WebClientResponseException e) {
        System.err.println("HTTP Status: " + e.getStatusCode());
        System.err.println("Status text : " + e.getStatusText());
        System.err.println("Response Body: " + e.getResponseBodyAsString());
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

}




