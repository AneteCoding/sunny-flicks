package com.sda.db.finalProject;

import java.sql.*;

public class DBOpsUtilities {

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

    public static void printAllDatabaseRecord(Connection connection) throws SQLException {
        String sql = "SELECT * from sunnyFlicks";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                double rating = resultSet.getInt("ratings");
                System.out.println(id + " | " + title + " | " + year + " | " + rating);
            }
        }
    }
    public static void deleteRecord(Connection connection, int id) throws SQLException {
        String sql = "DELETE from sunnyFlicks where id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record was successfully deleted");
            }
        }
    }
    public static void deleteTable(Connection connection, String tableName) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static void printRatingDatabaseRecordDescending(Connection connection) throws SQLException {
        String sql = "SELECT * from sunnyFlicks ORDER BY  ratings DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Top of the Sunny Flicks!");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                double userRating = resultSet.getInt("ratings");
                System.out.println(id + " | " + title + " | " + userRating);
            }
        }
    }



}
