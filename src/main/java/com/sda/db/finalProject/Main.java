package com.sda.db.finalProject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static com.sda.db.finalProject.DBConnection.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("We ain't able to connect to the database");
        } else {
            System.out.println("Welcome to 'Sunny Flicks' movie ratings!");
            System.out.println("Here you will find the 15 best ever sunny holiday films as recommended by 'Sunny Flicks'.");
//            System.out.println("Movie database");
//            DBOps.createTable(connection);
//            System.out.println("All records in the database");
//            DBOps.printAllDatabaseRecord(connection);
            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter movie title:");
//            String title = scanner.nextLine();
//            System.out.println("Enter release year: ");
//            String year = scanner.next();
//            System.out.println("Enter rating:");
//            double defaultRating = scanner.nextDouble();
//            DBOps.insertIntoTable(connection, title, year, defaultRating);
            DBOps.printAllDatabaseRecordNoRating(connection);
            DBOps.createRatingTable(connection);

            System.out.println("____________________________________________________________");
            System.out.println("Please use our app to rate for yourself!");
            int command = 1;
            do {
                System.out.println("First - select the movie ID: ");
                int movieId = scanner.nextInt();
                System.out.println("Now please rate the movie from 1-10: ");
                double userRating = scanner.nextDouble();
                DBOps.insertIntoRatingTable(connection, movieId, userRating);
                double avgRating = DBOps.getAverageRating(connection, movieId);
                DBOps.updateRecord(connection, avgRating, movieId);
                System.out.println("____________________________________________________________");
                System.out.println("If you would like to rate another movie, please press 1.\nTo Exit please enter any other number:");
                command = scanner.nextInt();
            } while ( command == 1);
            System.out.println("Thank you for taking part in 'Sunny Flicks' movie ratings!");
            System.out.println("____________________________________________________________");
            DBOps.printRatingDatabaseRecordDescending(connection);
            System.out.println("____________________________________________________________");
            DBOps.printMoviesWithMaxRating(connection);
            System.out.println("____________________________________________________________");
            DBOps.printMoviesWithMinRating(connection);
            System.out.println("____________________________________________________________");
            DBOps.ratingCount(connection);
            System.out.println("____________________________________________________________");

        }
    }
}