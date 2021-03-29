package com.sda.db.finalProject;

import java.sql.*;

public class DBOps {

    public static void createTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS sunnyFlicks(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(200)," +
                "year VARCHAR(4)," +
                "ratings DOUBLE" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
    public static void createRatingTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS userRatings(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "movieId INT," +
                "userRating DOUBLE" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }


    public static void insertIntoTable(Connection connection, String title, String year, double rating) throws SQLException {
        String sql = "INSERT INTO sunnyFlicks (title,year,ratings) VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, year);
            statement.setDouble(3, rating);
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

    public static void printAllDatabaseRecord(Connection connection) throws SQLException {
        String sql = "SELECT * from sunnyFlicks";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                double rating = resultSet.getInt("ratings");
                System.out.println(id + " | " + title + " | " + year + " | " +rating);
            }
        }
    }
    public static void printRatingDatabaseRecord(Connection connection) throws SQLException {
        String sql = "SELECT * from userRatings";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int movieId = resultSet.getInt("movieId");
                double userRating = resultSet.getInt("userRating");
                System.out.println(id + " | " + movieId+  " | " +userRating);
            }
        }
    }

    public static double getAverageRating(Connection connection,int movieId) throws SQLException {
        String sql = "SELECT * from userRatings where movieId=?";
        int count =0;
        double totalRatings =0.0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movieId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                double rating = resultSet.getInt("ratings");
                totalRatings=totalRatings+rating;
                count++;

            }
        }
        return totalRatings/count;
    }
    public static void deleteRecord(Connection connection, int id) throws SQLException {
        String sql = "DELETE from sunnyFlicks where id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record was successfully deleted");
            }
        }
    }
    public static void updateRecord(Connection connection, double rating, int id) throws SQLException {
        String sql = "UPDATE sunnyFlicks SET ratings=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, rating);
            statement.setInt(2, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record was successfully updated");
            }
        }
    }
    public static void deleteTable(Connection connection, String tableName) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

}

