package com.sda.db.practiceDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getConnection();

        if (connection == null) {
            System.out.println("We ain't able to connect to the database");
        } else {
            System.out.println("Oh yea!!! We did it!! We are connected!!! She finally goes Tech!!!!");
            System.out.println("All records in the database");
            DatabaseOperations.createTable(connection);
            DatabaseOperations.printAllDatabaseRecord(connection);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("Enter your address:");
            String address = scanner.nextLine();
            System.out.println("Enter your program");
            String program = scanner.nextLine();

            DatabaseOperations.insertIntoTable(connection, name, address, program);

            DatabaseOperations.updateRecord(connection, name, address, program);
            DatabaseOperations.printAllDatabaseRecord(connection);
            DatabaseOperations.deleteRecord(connection);
            DatabaseOperations.printAllDatabaseRecord(connection);


//            DatabaseOperations.createTable(connection);
//            DatabaseOperations.insertIntoTable(connection, name, address, program);
//            DatabaseOperations.deleteTable(connection, "student_record");
//            DatabaseOperations.createDatabase(connection, "sql_class");

        }
    }
}
