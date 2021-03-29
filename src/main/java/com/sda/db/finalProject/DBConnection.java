package com.sda.db.finalProject;

import java.sql.*;

public class DBConnection {

    /**
     * Datasource:
     * Database url = the vendor name, the server url&port, and the database name
     * Database name
     * Database username
     * Database password
     * <p>
     * "jdbc:mysql://localhost:3306/db_name"
     * mysql is the vendor name
     * the server url is localhost(i.e 127.0.0.1)
     * the port is 3306
     * database name is db_name
     */

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/she_goes_tech", "root", "aneteCoding");
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
