package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import com.project.week2.Utopia_airlines.JDBC_Utopia.DBTablePrinter;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingDao {
    private final Connection conn;

    public BookingDao (Connection connection) {
        this.conn = connection;
    }

    public static Booking getInput() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Ticket Number: ");
            int ticket_number = input.nextInt();
            System.out.print("Confirmation Code: ");
            String confirmation_code = input.next();
            System.out.print("Seat Type (First | Business | Economy): ");
            String seat_type = input.next();

            return new Booking(ticket_number, confirmation_code, seat_type);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect Input Type");
            return getInput();
        }
    }

    public void add(Booking booking) {
        String query =
                "INSERT INTO booking (ticket_number, is_active, confirmation_code, seat_type)" +
                        "VALUES (?,?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, booking.getTicket_number());
            statement.setBoolean(2, true);
            statement.setString(3, booking.getConfirmation_code());
            statement.setString(4, booking.getSeat_type());

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
    public void update(int id, Booking booking) {
        String query =
                "UPDATE booking " +
                        "SET ticket_number=?, is_active=?, confirmation_code=?, seat_type=? " +
                        "WHERE id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, booking.getTicket_number());
            statement.setBoolean(2, true);
            statement.setString(3, booking.getConfirmation_code());
            statement.setString(4, booking.getSeat_type());
            statement.setInt(4, id);

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

    public void overrideTrip(int id) {
        String query =
                "UPDATE booking " +
                        "SET is_active=? " +
                        "WHERE id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setBoolean(1, true);
            statement.setInt(2, id);

            try {
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("0 rows affected, make sure ID matches an ID in table");
                    System.out.println("-----------------------------------------------------");
                } else {
                    System.out.println("-----------------------------");
                    System.out.println(rowsAffected + " trip has been overridden.");
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

            int rowsAffected = stmt.executeUpdate("delete from booking where id = '" + id + "'");
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
        DBTablePrinter.printTable(conn, "booking");
    }
}
