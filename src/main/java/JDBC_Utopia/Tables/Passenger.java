package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class Passenger {
    private int booking_id;
    private String given_name;
    private String family_name;
    private Timestamp dob;
    private String gender;
    private String address;

    public List<String> passengerFields = List.of("booking_id", "given_name", "family_name", "dob", "gender", "address");

    public Passenger(int booking_id, String given_name, String family_name, Timestamp dob, String gender, String address) {
        this.booking_id = booking_id;
        this.given_name = given_name;
        this.family_name = family_name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }
}
