package ictgradschool.web.lab13.ex2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class FilmApp {

    public static void main(String[] args) {
        /* The following verifies that your JDBC driver is functioning. You may base your solution on this code */
        Scanner sc = new Scanner(System.in);
        String actor="";
        String movie="";
        String genre="";
        int selection=0;

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
            while (!(selection<=1) || !(selection>=4)) {
                System.out.println("Welcome to the Film database!\n" +
                        "Please select an option from the following:\n" +
                        "1. Information by Actor\n" +
                        "2. Information by Movie\n" +
                        "3. Information by Genre\n" +
                        "4. Exit");
                selection = Integer.parseInt(sc.nextLine());

                switch (selection){
                    case 1:
                        System.out.println("Please enter the name of the actor you wish to\n" +
                                "get information about, or press enter to return\n" +
                                "to the previous menu");

                        actor=sc.nextLine();

                        //getting the info from actor table foe requested actor
                        try (PreparedStatement pStmt = conn.prepareStatement(
                                "SELECT * FROM pfilms_actor WHERE actor_fname OR actor_lname LIKE ?")) {
                            pStmt.setString(1, "%" + actor + "%");

                            int actorId=0;

                            //getting the actor details
                            try (ResultSet rs = pStmt.executeQuery()) {
                                final int ID_COL = rs.findColumn("actor_id");
                                final int FN_COL = rs.findColumn("actor_fname");
                                final int LN_COL = rs.findColumn("actor_lname");

                                while (rs.next()) {
                                    actorId = rs.getInt(ID_COL);
                                    String actorFn = rs.getString(FN_COL);
                                    String actorLn = rs.getString(LN_COL);

                                    System.out.println(actorFn + " " + actorLn + " is listed as being involved in the following films: \n");
                                }

                                // getting the actor movies via the provided ID, might be able to do joined table query above
//                                try (PreparedStatement aStmt = conn.prepareStatement(
//                                        "SELECT * FROM pfilms_participates_in WHERE actor_id = ?" )){
//                                    aStmt.setInt(1,actorId);
//
//                                    try (ResultSet resultSet=aStmt.executeQuery()){
//                                        final int thing;
//
//                                    }

                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                }

                return;
                case 2:

                    return;
                case 3:

                    return;
                case 4:
                    return;
            }

        }










    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
