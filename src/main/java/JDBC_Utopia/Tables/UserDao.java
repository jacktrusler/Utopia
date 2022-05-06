package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import com.project.week2.Utopia_airlines.JDBC_Utopia.DBTablePrinter;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserDao {
    private final Connection conn;
    private static int role_id;

    public UserDao (Connection connection, int role_id) {
        this.conn = connection;
        this.role_id = role_id;
    }

    public static User getInput() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("given_name: ");
            String given_name = input.next();
            System.out.print("family_name: ");
            String family_name = input.next();
            System.out.print("username: ");
            String username = input.next();
            System.out.print("email: ");
            String email = input.next();
            System.out.print("password: ");
            String password = input.next();
            System.out.print("phone: ");
            String phone = input.next();

            return new User(role_id, given_name, family_name, username, email, password, phone);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect Input Type");
            return getInput();
        }
    }

    public void add(User user) {
        String query =
                "INSERT INTO user (role_id, given_name, family_name, username, email, password, phone)" +
                        "VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, role_id);
            statement.setString(2, user.getGiven_name());
            statement.setString(3, user.getFamily_name());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getPhone());

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
    public void update(int id, User user) {
        String query =
                "UPDATE user " +
                        "SET role_id=?, given_name=?, family_name=?, username=?, email=?, password=?, phone=?" +
                        "WHERE id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, role_id);
            statement.setString(2, user.getGiven_name());
            statement.setString(3, user.getFamily_name());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getPhone());
            statement.setInt(8, id);

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

            int rowsAffected = stmt.executeUpdate("delete from user where id = '" + id + "'");
            if (rowsAffected == 0) {
                System.out.println("-----------------------------------------------------");
                System.out.println("0 rows deleted, make sure ID matches an ID in table");
                System.out.println("-----------------------------------------------------");
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
        DBTablePrinter.printTable(conn, "user");
    }
}
