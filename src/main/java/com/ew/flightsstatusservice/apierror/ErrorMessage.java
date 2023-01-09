package com.ew.flightsstatusservice.apierror;

import java.util.Date;
public record ErrorMessage(int statusCode,
                           String message,
                           String description,
                           Date timestamp) {
}
