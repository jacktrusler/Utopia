package User_Types;

import java.sql.Connection;

public class Traveler {
    private final Connection conn;

    public Traveler(Connection connection){
        this.conn = connection;
    }
    public void printMsg() {
        System.out.println("You selected Traveler! What would you like to do?");
        System.out.println("1. Book A Ticket");
        System.out.println("2. Cancel an Upcoming Trip");
        System.out.println("3. Quit to Menu");
    }
}
