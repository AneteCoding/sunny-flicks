package com.sda.db.finalProject;

import java.sql.Connection;
import java.util.Scanner;

import static com.sda.db.finalProject.DBConnection.getConnection;

public class Table {

    public static void createTable() throws Exception {

        Connection connection = getConnection();
        Scanner scanner = new Scanner(System.in);

        DBOps.createTable(connection);
        System.out.println("Enter movie title:");
        String title = scanner.nextLine();
        System.out.println("Enter release year: ");
        String year = scanner.next();
        System.out.println("Enter rating:");
        double defaultRating = scanner.nextDouble();
        DBOps.insertIntoTable(connection, title, year, defaultRating);
    }
}
