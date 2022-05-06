package User_Types;

import java.sql.Connection;
import java.util.Scanner;

public class Employee {
    private Connection conn;
    public Employee(Connection connection){
        this.conn = connection;
    }
    public void printMsg() {
        System.out.println("You selected Employee! What would you like to do?");
        System.out.println("1. See Flights you manage");
        System.out.println("2. Quit to menu");
    }
}
