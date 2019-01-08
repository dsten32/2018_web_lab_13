package ictgradschool.web.lab13.ex1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Exercise01 {
    public static void main(String[] args) {
        /* The following verifies that your JDBC driver is functioning. You may base your solution on this code */
        Scanner sc = new Scanner(System.in);
        String title="";
        Properties dbProps = new Properties();

        try(FileInputStream fIn = new FileInputStream("mysql.properties")) {
            dbProps.load(fIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the database name to your database
        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("Connection successful");
            //Anything that requires the use of the connection should be in here...
            //setting up prepared statement
            while (title.length()==0) {
                System.out.println("what title do you want to search for?: ");
                title = sc.nextLine();

                try (PreparedStatement pStmt = conn.prepareStatement(
                        "SELECT * FROM lab13_articles WHERE title LIKE ?;")) {
                    pStmt.setString(1, "%" + title + "%");


                    try (ResultSet rs = pStmt.executeQuery()) {
                        int titleCol = rs.findColumn("title");
                        int bodyCol = rs.findColumn("body");

                        while (rs.next()) {
                            String retTitle = rs.getString(titleCol);
                            String retBody = rs.getString(bodyCol);

                            System.out.println(retTitle + "\n\n" + retBody + "\n\n");
                        }

                    }

                }
                title="";
            }






        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
