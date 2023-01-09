package com.ew.flightsstatusservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record FlightStatusResponse(String flight_iata,
                                   String dep_time,
                                   String dep_estimated,
                                   String dep_actual,
                                   String arr_time,
                                   String arr_estimated,
                                   String arr_actual,
                                   String status,
                                   String duration,
                                   String delayed,
                                   String dep_delayed,
                                   String arr_delayed,
                                   String updated,
                                   String dep_name,
                                   String dep_city,
                                   String dep_country,
                                   String arr_name,
                                   String arr_city,
                                   String arr_country,
                                   String airline_name
) {
}



