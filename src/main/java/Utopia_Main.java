import User_Types.Administrator;
import User_Types.Employee;
import User_Types.Traveler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Utopia_Main {
    static String url = "jdbc:mysql://localhost:3306/utopia?";
    static String user = "root";
    static String pass = "password";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, pass);

        Employee employee = new Employee(conn);
        Administrator admin = new Administrator(conn);
        Traveler traveler = new Traveler(conn);

        while (true) {
            ASCII_Utopia.printTitle();
            System.out.println("Welcome to Utopia Airlines! Please choose from one of the options below:");
            System.out.println("1. Employee\n2. Administrator\n3. Traveler\n4. Close the Program");
            Scanner scan = new Scanner(System.in);
            switch (scan.nextInt()) {
                case (1) -> employee.printMsg();

                case (2) -> admin.printMsg(true);

                case (3) -> traveler.printMsg();

                case (4) -> {return;}
            }
        }
    }
}
