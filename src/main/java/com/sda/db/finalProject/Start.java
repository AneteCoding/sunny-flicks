package com.sda.db.finalProject;

import java.sql.Connection;
import java.util.Scanner;

import static com.sda.db.finalProject.DBConnection.getConnection;

public class Start {

    public static void startApp() throws Exception{

        Connection connection = getConnection() ;
        Scanner scanner = new Scanner(System.in);

        System.out.println("__________________________________________________________________");
        System.out.println("Please use our app to rate for yourself!");
        int command = 1;
        do {
            System.out.println("First - please enter the number of your selected movie: ");
            int movieId = scanner.nextInt();
            if (movieId < 1 || movieId > 11) {
                System.out.println("!!! Please enter valid number of your chosen movie (1 - 16)!");
            } else {
                System.out.println("Now please rate the movie from 1-10: ");
                double userRating = scanner.nextDouble();
                if (userRating < 1 || userRating > 10) {
                    System.out.println("!!! Rating is not correct. Please start again.");
                } else {
                    DBOps.insertIntoRatingTable(connection, movieId, userRating);
                    double avgRating = DBOps.getAverageRating(connection, movieId);
                    DBOps.updateRecord(connection, avgRating, movieId);
                }
            }

            System.out.println("\nIf you would like to rate movie, please press 1.\nTo Exit please enter any other number!");
            command = scanner.nextInt();
        } while (command == 1);
        System.out.println("******************************************************************");
        System.out.println("Thank you for taking part in 'Sunny Flicks' movie ratings!");
    }

}
