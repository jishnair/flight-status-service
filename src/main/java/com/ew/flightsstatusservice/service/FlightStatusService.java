package com.ew.flightsstatusservice.service;

import com.ew.flightsstatusservice.apierror.exception.FlightNotFoundException;
import com.ew.flightsstatusservice.apierror.exception.FlightStatusServiceException;
import com.ew.flightsstatusservice.model.FlightStatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightStatusService {
    private final WebClient webClient;
    @Value("${airlabs.apikey}")
    private String airlabsApiKey;
    ObjectMapper objectMapper = new ObjectMapper();
    public Mono<FlightStatusResponse> getFlightStatus(final String flightNumber) {

        var responseStringMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flight")
                        .queryParam("flight_iata", flightNumber)
                        .queryParam("api_key", airlabsApiKey)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        return responseStringMono.map(responseString -> {
            JSONObject jsonObject = new JSONObject(responseString);

            if (jsonObject.has("error")) {
                JSONObject error = jsonObject.getJSONObject("error");
                log.error("Error code: " + error.getString("code") + " Error message: " + error.getString("message"));
                throw new FlightNotFoundException("Flight with number " + flightNumber + " not found");
            }

            JSONObject responseJson = jsonObject.getJSONObject("response");
            FlightStatusResponse flightStatusResponse;
            try {
                flightStatusResponse = objectMapper.readValue(responseJson.toString(), FlightStatusResponse.class);
            } catch (JsonProcessingException e) {
                log.error("Error while parsing response from airlabs: " + e.getMessage());
                throw new FlightStatusServiceException("Internal server error");
            }
            return flightStatusResponse;
        });

    }
}
