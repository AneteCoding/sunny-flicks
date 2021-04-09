package com.sda.db.finalProject;

import java.sql.Connection;
import java.util.Scanner;

import static com.sda.db.finalProject.DBConnection.getConnection;

public class Main {
    public static void main(String[] args) throws Exception {

        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("We ain't able to connect to the database");
        } else {
            System.out.println("Welcome to 'Sunny Flicks' movie ratings!");

//            Table.createTable();
//            DBOps.createRatingTable(connection);

            DBOps.printAllDatabaseRecordNoRating(connection);
            Start.startApp();
            DBOps.countTotalVotes(connection);
            Results.printMenu();
            Results.displayResults();
        }
    }
}