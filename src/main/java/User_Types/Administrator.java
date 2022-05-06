package User_Types;


import com.project.week2.Utopia_airlines.JDBC_Utopia.DBTablePrinter;
import com.project.week2.Utopia_airlines.JDBC_Utopia.Tables.*;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Administrator {
    private final Connection conn;

    private final List<String> tables = List.of("airport", "passenger", "flight", "user (employee)", "user (traveler)", "booking");

    public Administrator(Connection connection){
        this.conn = connection;
    }

    public static int getIdInt(){
        Scanner input = new Scanner(System.in);
        System.out.print("ID: ");
        return input.nextInt();
    }
    public static String getIdString(){
        Scanner input = new Scanner(System.in);
        System.out.print("ID: ");
        return input.next();
    }
    void formatTables(){
        tables.forEach(table -> System.out.print((1 + tables.indexOf(table)) + ". " + table + " | "));
        System.out.print("\n");
    }
    public void printMsg(boolean intro) {

        if (intro) {
            System.out.println("Welcome to the Administrator Menu! What would you like to do?");
        }

        System.out.println("1. Add to a Table");
        System.out.println("2. Update a Row in a Table ");
        System.out.println("3. Delete Row from a Table");
        System.out.println("4. Read From a Table");
        System.out.println("5. Override Trip Cancellation for ticket");
        System.out.println("6. Quit to menu");

        Scanner scan = new Scanner(System.in);
        int switchStatement;
        try {
            switchStatement = scan.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //Instantiate all DAOs with a single final conn
        FlightDao fDao = new FlightDao(conn);
        AirportDao aDao = new AirportDao(conn);
        BookingDao bDao = new BookingDao(conn);
        PassengerDao pDao = new PassengerDao(conn);
        UserDao eDao = new UserDao(conn, 1);
        UserDao tDao = new UserDao(conn, 3);

        switch (switchStatement) {
            case (1) -> {
                System.out.println("\nWhat table would you like to Add to?");
                formatTables();
                int tableIndex1 = scan.nextInt() - 1;
                if (tableIndex1 <= tables.size() - 1) {

                    String tableName1 = tables.get(tableIndex1);
                    switch (tableName1) {
                        case ("flight") -> fDao.add(FlightDao.getInput());
                        case ("airport") -> aDao.add("airport", AirportDao.getInput());
                        case ("passenger") -> pDao.add(PassengerDao.getInput());
                        case ("booking") -> bDao.add(BookingDao.getInput());
                        case ("user (employee)") -> eDao.add(UserDao.getInput());
                        case ("user (traveler)") -> tDao.add(UserDao.getInput());
                    }
                }
                this.printMsg(false);
            }
            case (2) -> {
                System.out.println("\nWhat table would you like to Update?");
                formatTables();
                int tableIndex2 = scan.nextInt() - 1;
                if (tableIndex2 <= tables.size() - 1) {

                    String tableName2 = tables.get(tableIndex2);
                    switch (tableName2) {
                        case ("flight") -> fDao.update(getIdInt(), FlightDao.getInput());
                        case ("airport") -> aDao.update(getIdString(), AirportDao.getInput());
                        case ("passenger") -> pDao.update(getIdInt(), PassengerDao.getInput());
                        case ("booking") -> bDao.update(getIdInt(), BookingDao.getInput());
                        case ("user (employee)") -> eDao.update(getIdInt(), UserDao.getInput());
                        case ("user (traveler)") -> tDao.update(getIdInt(), UserDao.getInput());
                    }
                }
                this.printMsg(false);
            }

            case (3) -> {
                System.out.println("\nWhat table would you like to Delete From?");
                formatTables();
                int tableIndex3 = scan.nextInt() - 1;
                if (tableIndex3 <= tables.size() - 1) {
                    String tableName2 = tables.get(tableIndex3);
                    switch (tableName2) {
                    case ("flight") -> fDao.delete(getIdInt());
                    case ("airport") -> aDao.delete(getIdString());
                    case ("passenger") -> pDao.delete(getIdInt());
                    case ("booking") -> bDao.delete(getIdInt());
                    case ("user (employee)") -> eDao.delete(getIdInt());
                    case ("user (traveler)") -> tDao.delete(getIdInt());
                    }
                }
                this.printMsg(false);
            }

            case (4) -> {
                System.out.println("\nWhat table would you like to Read?");
                formatTables();
                int tableIndex4 = scan.nextInt() - 1;
                if (tableIndex4 <= tables.size() - 1) {

                    String tableName1 = tables.get(tableIndex4);
                    switch (tableName1) {
                        case ("airport") -> DBTablePrinter.printTable(conn, "airport");
                        case ("passenger") -> DBTablePrinter.printTable(conn, "passenger");
                        case ("flight") -> DBTablePrinter.printTable(conn, "flight");

                        case ("user (employee)") -> {
                            try {
                                Statement stmt = conn.createStatement();
                                ResultSet rset = stmt.executeQuery("select * from user WHERE role_id=1");
                                DBTablePrinter.printResultSet(rset);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        case ("user (traveler)") -> {
                            try {
                                Statement stmt = conn.createStatement();
                                ResultSet rset = stmt.executeQuery("select * from user WHERE role_id=3");
                                DBTablePrinter.printResultSet(rset);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        case ("booking") -> DBTablePrinter.printTable(conn, "booking");
                        default -> System.out.println("no table available at this index");
                    }
                }
                this.printMsg(false);
            }

            case (5) -> {
                System.out.println("\nOverride Trip Cancellation?");
                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rset = stmt.executeQuery("select * from booking WHERE is_active=0");
                    DBTablePrinter.printResultSet(rset);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                bDao.overrideTrip(getIdInt());
                this.printMsg(false);
            }
            case (6) -> {return;}

            default ->
                System.out.println("Number out of range");
        }
    }
}
