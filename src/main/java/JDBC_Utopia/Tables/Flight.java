package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class Flight {
    private int route_id;
    private int airplane_id;
    private int reserved_seats;
    private float seat_price;

    public List<String> flightFields = List.of("Route ID: ", "Airplane ID: ", "Reserved Seats: ", "Seat Price: ");

    public Flight(int route_id, int airplane_id, int reserved_seats, float seat_price) {
        this.route_id = route_id;
        this.airplane_id = airplane_id;
        this.reserved_seats = reserved_seats;
        this.seat_price = seat_price;
    }
}
