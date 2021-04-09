package com.sda.db.finalProject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static com.sda.db.finalProject.DBConnection.getConnection;

public class Results {

    public static void printMenu() {

        System.out.println("\nWould you like to see the summary of the results?");
        System.out.println("Your choices are:\n1- to see the best rated movie\n2- to see the lowest rated movie\n" +
                "3- to see the full list of results so far\n4- search movie by name\n0- to Exit our app.");
        System.out.println("__________________________________________________________________");
    }

    public static void displayResults() throws SQLException {

        Connection connection = getConnection();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("Please enter your choice (0-4)");
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
                case 4:
                    System.out.println("Please enter the name of the movie:");
                    String searchString = scanner.next();
                    DBOps.printMoviesWithMatchingTitle(connection, searchString);
                    break;
                case 0:
                    quit = true;
                    System.out.println("Thank you for using our app!");
            }
        }
    }
}

