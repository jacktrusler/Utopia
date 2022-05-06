package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import com.project.week2.Utopia_airlines.JDBC_Utopia.DBTablePrinter;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class AirportDao {
    private final Connection conn;


    public AirportDao (Connection connection) {
        conn = connection;
    }

    public static Airport getInput() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("International Air Transport Association ID (IATA): ");
            String iata_id = input.next();
            if (iata_id.length() != 3) {
                System.out.println("IATA must be exactly 3 characters");
                return getInput();
            }

            System.out.println("City: ");
            String city = input.next();
            return new Airport(iata_id, city);

        } catch (InputMismatchException e) {
            System.out.println("Incorrect Input Type");
            return getInput();
        }
    }

    public void add(String tableName, Airport airport) {
        String query =
                "INSERT INTO " + tableName + " (iata_id, city)" +
                        "VALUES (?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, airport.getIata_id().toUpperCase(Locale.ROOT));
            statement.setString(2, airport.getCity());

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
    public void update(String id, Airport airport) {
        String query =
                "UPDATE airport " +
                        "SET iata_id=?, city=?" +
                        "WHERE id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, airport.getIata_id());
            statement.setString(2, airport.getCity());
            statement.setString(3, id);

            try {
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("0 rows affected, make sure ID matches an ID in table");
                    System.out.println("-----------------------------------------------------");
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

    public void delete(String iata_id){

        try {
            Statement stmt = conn.createStatement();

            int rowsAffected = stmt.executeUpdate("delete from airport where iata_id = '" + iata_id + "'");
            if (rowsAffected == 0) {
                System.out.println("----------------------------------------------------");
                System.out.println("0 rows deleted, make sure ID matches an ID in table");
                System.out.println("----------------------------------------------------");
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
        DBTablePrinter.printTable(conn, "airport");
    }

    public void createTable(String tableName){
        /** "Create table [tableName] (iata_id CHAR(3), city)" */
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("create table " + tableName +" (iata_id CHAR(3) PRIMARY KEY, city VARCHAR(100))");
            System.out.println("-----------------------------");
            System.out.println(tableName + " has been created!");
            System.out.println("-----------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropTable(String tableName){
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("drop table " + tableName +" ");
                System.out.println("-----------------------------");
                System.out.println(tableName + " has been dropped.");
                System.out.println("-----------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
