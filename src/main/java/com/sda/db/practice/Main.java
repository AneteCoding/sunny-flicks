package com.sda.db.practice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnection.getConnection();

        if (connection == null) {
            System.out.println("Connection failed");
        } else {
            System.out.println("Connection successful");

            //Songs.deleteTable(connection,"songs");
            //Songs.createTable(connection);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter song title");
            String title = scanner.nextLine();
            System.out.println("Enter artist name");
            String artist = scanner.nextLine();
            System.out.println("Enter album title");
            String album = scanner.nextLine();

            Songs.insertIntoTable(connection, title, artist, album);
            //Songs.updateInput(connection,title,artist,album,2);
            //Songs.deleteInput(connection, 3);
            Songs.printAllSongs(connection);


        }

    }
}
