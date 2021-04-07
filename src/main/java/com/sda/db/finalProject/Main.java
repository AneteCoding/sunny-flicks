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
            System.out.println("Here you will find the 15 best ever sunny holiday movies as recommended by 'Sunny Flicks'.\n");
//            DBOps.createTable(connection);
            DBOps.printAllDatabaseRecordNoRating(connection);
            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter movie title:");
//            String title = scanner.nextLine();
//            System.out.println("Enter release year: ");
//            String year = scanner.next();
//            System.out.println("Enter rating:");
//            double defaultRating = scanner.nextDouble();
//            DBOps.insertIntoTable(connection, title, year, defaultRating);
//            DBOps.printAllDatabaseRecordNoRating(connection);
//            DBOps.createRatingTable(connection);

            System.out.println("____________________________________________________________");
            System.out.println("Please use our app to rate for yourself!");
            int command = 1;
            do {
                System.out.println("First - please enter the number of your selected movie: ");
                int movieId = scanner.nextInt();
                System.out.println("Now please rate the movie from 1-10: ");
                double userRating = scanner.nextDouble();
                DBOps.insertIntoRatingTable(connection, movieId, userRating);
                double avgRating = DBOps.getAverageRating(connection, movieId);
                DBOps.updateRecord(connection, avgRating, movieId);
                System.out.println("____________________________________________________________");
                System.out.println("If you would like to rate another movie, please press 1.\nTo Exit please enter any other number:");
                command = scanner.nextInt();
            } while (command == 1);
            System.out.println("Thank you for taking part in 'Sunny Flicks' movie ratings!");
            DBOps.countTotalVotes(connection);
            System.out.println("---------------------------------------------------------------");

            System.out.println("Please enter the movie");
            String searchString= scanner.next();
            DBOps.printMoviesWithMatchingTitle(connection,searchString);


            Results.displayResults();
            boolean quit = false;
            while (!quit) {
                System.out.println("Please enter your choice (0-3)");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        DBOps.printMoviesWithMaxRating(connection);
                        break;
                    case 2:
                        DBOps.printMoviesWithMinRating(connection);
                        break;
                    case 3:
                        DBOps.ratingCount(connection);
                        break;
//                    case 4: DBOps.searchMovie(connection);
//                    break;
                    case 0:
                        quit = true;
                        System.out.println("Thank you for using our app!");
                }
            }


        }

    }


}