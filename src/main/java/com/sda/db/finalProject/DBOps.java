package com.sda.db.finalProject;

import java.sql.*;

public class DBOps {

    public static void insertIntoTable(Connection connection, String title, String year, double defaultRating) throws SQLException {
        String sql = "INSERT INTO sunnyFlicks (title,year,ratings) VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, year);
            statement.setDouble(3, defaultRating);
            boolean isSuccessful = statement.execute();
            if (isSuccessful) {
                System.out.println("Record was successfully inserted");
            }
        }
    }

    public static void insertIntoRatingTable(Connection connection, int movieID, double userRating) throws SQLException {
        String sql = "INSERT INTO userRatings (movieID, userRating) VALUES(?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movieID);
            statement.setDouble(2, userRating);
            boolean isSuccessful = statement.execute();
            if (isSuccessful) {
                System.out.println("Record was successfully inserted");
            }
        }
    }

    public static void printAllDatabaseRecordNoRating(Connection connection) throws SQLException {
        String sql = "SELECT * from sunnyFlicks";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Here you will find the 15 best ever sunny holiday movies as recommended by 'Sunny Flicks'.\n");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                System.out.println(id + " | " + title + " | " + year);
            }
        }
    }

    public static double getAverageRating(Connection connection, int movieId) throws SQLException {
        String sql = "SELECT * from userRatings where movieId=?";
        int count = 0;
        double totalRatings = 0.0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movieId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                double rating = resultSet.getInt("userRating");
                totalRatings = totalRatings + rating;
                count++;
            }
        }
        return totalRatings / count;
    }

    public static void updateRecord(Connection connection, double avgRating, int id) throws SQLException {
        String sql = "UPDATE sunnyFlicks SET ratings=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, avgRating);
            statement.setInt(2, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Thank you! Your rating has been successfully submitted!");
            }
        }
    }

    public static double getMaxRating(Connection connection) throws SQLException {
        double maxRating = 0.0;
        String sql = "SELECT MAX(ratings) AS  bestRating from sunnyFlicks ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                maxRating = resultSet.getDouble("bestRating");
            }
        }
        return maxRating;
    }

    public static void printMoviesWithMaxRating(Connection connection) throws SQLException {
        double maxRating = getMaxRating(connection);
        String sql = "SELECT * from sunnyFlicks WHERE ratings=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, maxRating);
            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("This is the highest rated movie:");
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String year = resultSet.getString("year");
                    double rating = resultSet.getDouble("ratings");
                    System.out.printf("%s | %s | %.1f\n", title, year, rating);
                }
            }
        }
    }


    public static void printMoviesWithMatchingTitle(Connection connection, String searchString) throws SQLException {

        String sql = "SELECT * from sunnyFlicks WHERE title LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + searchString + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next() ) {
                System.out.println("Sorry, we could not find any movie with this title.\nTry again.");
            } else {
                System.out.println("This is the matching movie with entered title:");
                do {
                    String title = resultSet.getString("title");
                    String year = resultSet.getString("year");
                    double rating = resultSet.getDouble("ratings");
                    System.out.printf("%s | %s | rating: %.1f\n", title, year, rating);
                } while (resultSet.next());
            }
        }
    }

    public static double getMinRating(Connection connection) throws SQLException {
        double minRating = 0.0;
        String sql = "SELECT MIN(ratings) AS  lowestRating from sunnyFlicks ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                minRating = resultSet.getDouble("lowestRating");
            }
        }
        return minRating;
    }

    public static void printMoviesWithMinRating(Connection connection) throws SQLException {
        double minRating = getMinRating(connection);
        String sql = "SELECT * from sunnyFlicks WHERE ratings=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, minRating);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("This is the lowest rated movie:");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                double rating = resultSet.getDouble("ratings");
                System.out.printf("%s | %s | %.1f\n", title, year, rating);
            }
        }
    }


    public static void countTotalVotes(Connection connection) throws SQLException {
        String sql = "SELECT COUNT(movieId) AS voteCount FROM userRatings";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int totalVotes = resultSet.getInt("voteCount");
                System.out.println("*****************************************************************");
                System.out.println("Thank you for taking part in 'Sunny Flicks' movie ratings!");
                System.out.printf("\nTotal votes received in 'Sunny Flicks' movie ratings so far: %d.\n", totalVotes);
                System.out.println("*****************************************************************");
            }
        }
    }

    public static void ratingCount(Connection connection) throws SQLException {
        String sql = "SELECT COUNT(movieId) voteCount, sunnyFlicks.id, sunnyFlicks.title, sunnyFlicks.year, sunnyFlicks.ratings " +
                "FROM userRatings RIGHT JOIN sunnyFlicks ON userRatings.movieId=sunnyFlicks.id GROUP BY movieId " +
                "ORDER BY sunnyFlicks.ratings DESC, sunnyFlicks.title";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                System.out.println("**************** Final results ****************");
                while (resultSet.next()) {
                    int voteCount = resultSet.getInt("voteCount");
                    String title = resultSet.getString("title");
                    String year = resultSet.getString("year");
                    double ratings = resultSet.getDouble("ratings");

                    if (voteCount == 1) {
                        System.out.printf("%s | %s | %.1f | %d vote\n", title, year, ratings, voteCount);

                    } else {
                        System.out.printf("%s | %s | %.1f | %d votes\n", title, year, ratings, voteCount);

                    }
                }
            }
        }
    }
}
