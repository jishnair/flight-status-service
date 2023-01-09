package com.ew.flightsstatusservice.controller;

import com.ew.flightsstatusservice.model.FlightStatusResponse;
import com.ew.flightsstatusservice.service.FlightStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FlightStatusControllerTest {

    private FlightStatusService flightStatusServiceMock;

    private FlightStatusController flightStatusController;

    @BeforeEach
    public void setup() {
        flightStatusServiceMock = Mockito.mock(FlightStatusService.class);
        flightStatusController = new FlightStatusController(flightStatusServiceMock);
    }

    @Test
    void getFlightStatus() {

        FlightStatusResponse flightStatusResponse = FlightStatusResponse.builder()
                .flight_iata("EW9345")
                .status("On Time")
                .airline_name("Eurowings")
                .arr_country("DE")
                .arr_city("Berlin")
                .build();

        Mockito.when(flightStatusServiceMock.getFlightStatus("EW9345")).thenReturn(Mono.just(flightStatusResponse));

        StepVerifier.create(flightStatusController.getFlightStatus("EW9345").getBody())
                .expectNextMatches(response -> response.flight_iata().equals("EW9345")
                        && response.status().equals("On Time")
                        && response.airline_name().equals("Eurowings")
                        && response.arr_country().equals("DE")
                        && response.arr_city().equals("Berlin"))
                .verifyComplete();
    }
}
