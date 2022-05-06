package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class Airport {
    private String iata_id;
    private String city;

    public List<String> airportFields = List.of("iata_id", "city");

    public Airport(String iata_id, String city) {
        if (iata_id.trim().length() > 3){
            System.out.println("iata_id must be 3 characters or less");
            return;
        }
        this.iata_id = iata_id.trim();
        this.city = city.trim();
    }
}
