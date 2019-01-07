package ictgradschool.web.lab13.ex1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Exercise01 {
    public static void main(String[] args) {
        /* The following verifies that your JDBC driver is functioning. You may base your solution on this code */
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




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
