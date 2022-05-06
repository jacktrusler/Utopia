package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import com.project.week2.Utopia_airlines.JDBC_Utopia.DBTablePrinter;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PassengerDao {
    private final Connection conn;

    public PassengerDao (Connection connection) {
        this.conn = connection;
    }

    public static Passenger getInput() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("booking_id: ");
            int booking_id = input.nextInt();
            System.out.print("given_name: ");
            String given_name = input.next();
            System.out.print("family_name: ");
            String family_name = input.next();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = input.next();
            System.out.print("Gender: ");
            String gender = input.next();
            System.out.print("Address: ");
            String address = input.next();

            return new Passenger(booking_id, given_name, family_name, Timestamp.valueOf(dob + " 00:00:00"), gender, address);

        } catch (InputMismatchException e) {
            System.out.println("Incorrect Input Type");
            return getInput();
        }


    }

    public void add(Passenger passenger) {
        String query =
                "INSERT INTO flight (booking_id, given_name, family_name, dob, gender, address)" +
                        "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, passenger.getBooking_id());
            statement.setString(2, passenger.getGiven_name());
            statement.setString(3, passenger.getFamily_name());
            statement.setTimestamp(4, passenger.getDob());
            statement.setString(5, passenger.getGender());
            statement.setString(6, passenger.getAddress());

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
    public void update(int id, Passenger passenger) {
        String query =
                "UPDATE passenger " +
                       "SET booking_id=?, given_name=?, family_name=?, dob=?, gender=?, address=?" +
                        "WHERE id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, passenger.getBooking_id());
            statement.setString(2, passenger.getGiven_name());
            statement.setString(3, passenger.getFamily_name());
            statement.setTimestamp(4, passenger.getDob());
            statement.setString(5, passenger.getGender());
            statement.setString(6, passenger.getAddress());
            statement.setInt(7, id);

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

    public void delete(int id){

        try {
            Statement stmt = conn.createStatement();

            int rowsAffected = stmt.executeUpdate("delete from passenger where id = '" + id + "'");
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
        DBTablePrinter.printTable(conn, "passenger");
    }
}
