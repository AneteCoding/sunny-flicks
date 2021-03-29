package com.sda.db.finalProject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnection.getConnection();
        if (connection == null) {
            System.out.println("We ain't able to connect to the database");
        } else {

//            System.out.println("Movie database");
//            //DBOps.createTable(connection);
//            System.out.println("All records in the database");
            DBOps.printAllDatabaseRecord(connection);
            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter movie title:");
//            String title = scanner.nextLine();
//            System.out.println("Enter release year: ");
//            String year = scanner.next();
//            System.out.println("Enter rating:");
//            double defaultRating = scanner.nextDouble();
//            DBOps.insertIntoTable(connection, title, year, defaultRating);
//            DBOps.printAllDatabaseRecord(connection);
//            double ratingUser= 6; //we have to create method
//            int movieID= 2; //method here as well
//            DBOps.updateRecord(connection,ratingUser,movieID);
            DBOps.createRatingTable(connection);
            System.out.println("Welcome rating app. Please select movie ID");
            int movieId =scanner.nextInt();
            System.out.println("Please rate the movie from 1-10");
            double userRating = scanner.nextDouble();
            DBOps.insertIntoRatingTable(connection,movieId,userRating);
            DBOps.printRatingDatabaseRecord(connection);
            double avgRating=DBOps.getAverageRating(connection,movieId);
            DBOps.updateRecord(connection,avgRating,movieId);



        }
    }
}
