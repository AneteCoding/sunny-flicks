package com.sda.db.finalProject;

import java.sql.Connection;
import java.util.Scanner;

import static com.sda.db.finalProject.DBConnection.getConnection;

public class Start {

    public static void startApp() throws Exception {

        Connection connection = getConnection();
        Scanner scanner = new Scanner(System.in);

        System.out.println("__________________________________________________________________");
        System.out.println("Please use our app to rate for yourself!");
        int command = 1;

        while (command == 1) {
            System.out.println("Please enter the number of your selected movie (1-15): ");
            int movieId = scanner.nextInt();
            if (movieId < 1 || movieId > 15) {
                System.out.println("!!! Number not found!");
            } else {
                rateMovie(movieId);
                System.out.println("\nIf you would like to rate another movie, please press 1.\nTo Exit please enter any other number!");
                command = scanner.nextInt();
            }
        }
    }

    public static void rateMovie(int movieId) throws Exception {
        Connection connection = getConnection();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Please rate the movie (1-10)");
            double userRating = scanner.nextDouble();
            if (userRating < 1 || userRating > 10) {
                System.out.println("!!! Rating is not correct.");
            } else {
                DBOps.insertIntoRatingTable(connection, movieId, userRating);
                double avgRating = DBOps.getAverageRating(connection, movieId);
                DBOps.updateRecord(connection, avgRating, movieId);
                break;
            }
        }
    }
}


