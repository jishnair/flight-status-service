package com.ew.flightsstatusservice.service;

import com.ew.flightsstatusservice.apierror.exception.FlightNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FlightStatusServiceTest {
    private ClientAndServer mockServer;
    private FlightStatusService flightStatusService;

    private static final ObjectMapper serializer = new ObjectMapper();

    @BeforeEach
    public void setupMockServer() {
        mockServer = ClientAndServer.startClientAndServer(2001);
        flightStatusService = new FlightStatusService(WebClient.builder()
                .baseUrl("http://localhost:" + mockServer.getLocalPort()).build());

    }

    @AfterEach
    public void tearDownServer() {
        mockServer.stop();
    }

    @Test
    @DisplayName("Should return correct flight status")
    public void testGetFlightStatusSuccess() throws IOException {
        Resource resource = new ClassPathResource("flightStatusResponse.json");
        File file = resource.getFile();
        String responseBody = new String(Files.readAllBytes(file.toPath()));

        mockServer.when(
                org.mockserver.model.HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/flight")
                        .withQueryStringParameter("flight_iata", "EW9345")
        ).respond(
                org.mockserver.model.HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(responseBody)
        );

        var response = flightStatusService.getFlightStatus("EW9345").block();
        assert response != null;
        assertEquals("EW9345", response.flight_iata());
        assertEquals("Eurowings", response.airline_name());
    }

    @Test
    @DisplayName("Should throw FlightNotFoundException")
    public void testGetFlightStatusNotFound() throws IOException {
        Resource resource = new ClassPathResource("flightStatusError.json");
        File file = resource.getFile();
        String responseBody = new String(Files.readAllBytes(file.toPath()));

        mockServer.when(
                org.mockserver.model.HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/flight")
                        .withQueryStringParameter("flight_iata", "Test")
        ).respond(
                org.mockserver.model.HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(responseBody)
        );
        assertThrows(FlightNotFoundException.class, () -> flightStatusService.getFlightStatus("Test").block());
    }

}
