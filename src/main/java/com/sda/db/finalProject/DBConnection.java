package com.sda.db.finalProject;

import java.sql.*;

public class DBConnection {


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/she_goes_tech", "root", "password");
    }

    public static Statement createStatement() throws SQLException {
        Connection connection = getConnection();
        return connection.createStatement();
    }

    public static PreparedStatement createPreparedStatement() throws SQLException {
        Connection connection = getConnection();
        return connection.prepareStatement("");
    }

    public static CallableStatement createCallableStatement() throws SQLException {
        Connection connection = getConnection();
        return connection.prepareCall("");
    }

    public static void usingResultSet() throws SQLException {
        Statement statement = null;
        try {
            statement = createStatement();
            ResultSet resultSet = statement.executeQuery("");

            while (resultSet.next()) {
                //keep retrieving

            }

        } finally {
            statement.close();
        }
    }
}


