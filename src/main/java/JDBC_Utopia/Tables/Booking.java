package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class Booking {
    private int ticket_number;
    private String confirmation_code;
    private String seat_type;
    public List<String> bookingFields = List.of("ticket_number", "confirmation_code", "seat_type");

    public Booking(int ticket_number, String confirmation_code, String seat_type) {
        this.ticket_number = ticket_number;
        this.confirmation_code = confirmation_code.trim();
        this.seat_type = seat_type.trim();
    }
}