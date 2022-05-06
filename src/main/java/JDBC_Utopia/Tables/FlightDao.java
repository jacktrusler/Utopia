package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import com.project.week2.Utopia_airlines.JDBC_Utopia.DBTablePrinter;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FlightDao {
    private final Connection conn;

    public FlightDao (Connection connection) {
        conn = connection;
    }

    public static Flight getInput() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Route ID: ");
            int route_id = input.nextInt();
            System.out.print("Airplane ID: ");
            int airplane_id = input.nextInt();
            System.out.print("Reserved Seats: ");
            int reserved_seats = input.nextInt();
            System.out.print("Price (no $ sign): ");
            float seat_price = input.nextFloat();

            return new Flight(route_id, airplane_id, reserved_seats, seat_price);

        } catch (InputMismatchException e) {
            System.out.println("Incorrect Input Type");
            return getInput();
        }
    }

        public void add(Flight flight) {
        String query =
                "INSERT INTO flight (route_id, airplane_id, departure_time, reserved_seats, seat_price)" +
                        "VALUES (?,?,?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, flight.getRoute_id());
            statement.setInt(2, flight.getAirplane_id());
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(4, flight.getReserved_seats());
            statement.setFloat(5, flight.getSeat_price());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("0 rows added, make sure your input syntax is correct.");
                    System.out.println("-----------------------------------------------------");
                } else {
                    System.out.println("-----------------------------");
                    System.out.println(rowsAffected + " row has been added.");
                    System.out.println("-----------------------------");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int id, Flight flight) {
        String query =
                "UPDATE flight " +
                        "SET route_id=?, airplane_id=?, departure_time=?, reserved_seats=?, seat_price=?" +
                        "WHERE id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, flight.getRoute_id());
            statement.setInt(2, flight.getAirplane_id());
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(4, flight.getReserved_seats());
            statement.setFloat(5, flight.getSeat_price());
            statement.setInt(6, id);

            try {
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("0 rows affected, make sure ID matches an ID in Flights table");
                    System.out.println("------------------------------------------------------------");
                } else {
                    System.out.println("-----------------------------");
                    System.out.println(rowsAffected + " row has been updated.");
                    System.out.println("-----------------------------");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){

        try {
            Statement stmt = conn.createStatement();

            int rowsAffected = stmt.executeUpdate("delete from flight where id = '" + id + "'");
            if (rowsAffected == 0) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("0 rows deleted, make sure ID matches an ID in Flights table");
                System.out.println("-----------------------------------------------------------");
            } else {
                System.out.println("-----------------------------");
                System.out.println(rowsAffected + " row has been deleted.");
                System.out.println("-----------------------------");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        DBTablePrinter.printTable(conn, "flight");
    }
}
