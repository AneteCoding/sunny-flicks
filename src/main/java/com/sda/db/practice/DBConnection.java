package com.sda.db.practice;

import java.sql.*;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/she_goes_tech", "root", "aneteCoding");
    }
    public static Statement createStatement() throws SQLException {
        Connection connection = getConnection();
        return connection.createStatement();
    }
    public static PreparedStatement createPreparedStatement() throws SQLException {
        Connection connection = getConnection();
        return connection.prepareStatement("");
    }
}
