package com.sda.db;

import java.sql.*;

public class DatabaseOperations {

    public static void createTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS student(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "first_name VARCHAR(200)," +
                "address TEXT," +
                "program VARCHAR(255)" +
                ")";

        //Try with resources
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }


    public static void deleteTable(Connection connection, String tableName) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static void createDatabase(Connection connection, String dbName) throws SQLException {
        String sql = "CREATE DATABASE " + dbName;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
    

    /**
     *
     * --------------------------------------------------------------- CRUD operations -------------------------------------
    * C -> CREATE
    * */
    public static void insertIntoTable(Connection connection, String name, String address, String program) throws SQLException {
        String sql = "INSERT INTO student(first_name,address,program) VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, program);

            boolean isSuccessful = statement.execute();
            if (isSuccessful) {
                System.out.println("Record was successfully inserted");
            }
        }
    }

    /**
     * R -  READ
     * */
    public static void printAllDatabaseRecord(Connection connection) throws SQLException {
        String sql = "SELECT * from student";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("first_name");
                String address = resultSet.getString("address");
                String program = resultSet.getString("program");

                System.out.println(id + " | " + name + " | " + address + " | " + program);
            }
        }
    }

    /**
     * U - Update
     * */
    public static void updateRecord(Connection connection, String name, String address, String program) throws SQLException {
        String sql = "UPDATE student SET first_name=?, address=?, program=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, program);
            statement.setInt(4, 1);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record was successfully updated");
            }
        }
    }

    /**
     * D - DELETE
     * */
    public static void deleteRecord(Connection connection) throws SQLException {
        String sql = "DELETE from student where id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 1);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record was successfully deleted");
            }
        }
    }
}
