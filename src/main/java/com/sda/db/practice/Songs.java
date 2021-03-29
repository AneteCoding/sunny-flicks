package com.sda.db.practice;

import java.sql.*;

public class Songs {

    public static void createTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS songs (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(150)," +
                "artist VARCHAR(150)," +
                "album TEXT" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static void insertIntoTable(Connection connection, String title, String artist, String album) throws SQLException {
        String sql = "INSERT INTO songs(title, artist, album) VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, album);

            boolean isSuccessful = statement.execute();
            if (isSuccessful) {
                System.out.println("Record added");
            }
        }

    }

    public static void deleteTable(Connection connection, String tableName) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static void updateInput(Connection connection, String title, String artist, String album, int id) throws SQLException {
        String sql = "UPDATE songs SET title=?,artist=?,album=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, album);
            statement.setInt(4, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record was successfully updated");
            }
        }
    }

    public static void deleteInput(Connection connection, int id) throws SQLException {
        String sql = "DELETE from songs WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record was successfully deleted");
            }
        }
    }
    public static void printAllSongs(Connection connection) throws SQLException {
        String sql = "SELECT * from songs";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+" | "+ resultSet.getString("title")+ " | "+ resultSet.getString("artist")+" | "+resultSet.getString("album"));
            }
        }
    }

}
