package com.ew.flightsstatusservice.controller;

import com.ew.flightsstatusservice.model.FlightStatusResponse;
import com.ew.flightsstatusservice.service.FlightStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flights")
@Slf4j
@RequiredArgsConstructor
public class FlightStatusController {
    private final FlightStatusService flightStatusService;

    @GetMapping("/status")
    public ResponseEntity<Mono<FlightStatusResponse>> getFlightStatus(@RequestParam String flightNumber) {
        log.info("Received request for flight status for flight number: {}", flightNumber);
        var flightStatusResponse = flightStatusService.getFlightStatus(flightNumber);
        log.info("Flight status response for flight number: {} is successful", flightNumber);
        return new ResponseEntity<>(flightStatusResponse, HttpStatus.OK);
    }
}
