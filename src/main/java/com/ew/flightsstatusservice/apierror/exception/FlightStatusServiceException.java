package com.ew.flightsstatusservice.apierror.exception;

public class FlightStatusServiceException extends RuntimeException {
    public FlightStatusServiceException(String message) {
        super(message);
    }
}
