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
        String actor = "";
        String movie = "";
        String genre = "";
        int selection = 0;

        Properties dbProps = new Properties();

        try (FileInputStream fIn = new FileInputStream("mysql.properties")) {
            dbProps.load(fIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the database name to your database
        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("Connection successful");
            //Anything that requires the use of the connection should be in here...
            //setting up prepared statement
            while (!(selection <= 1) || !(selection >= 4)) {
                System.out.println("Welcome to the Film database!\n" +
                        "Please select an option from the following:\n" +
                        "1. Information by Actor\n" +
                        "2. Information by Movie\n" +
                        "3. Information by Genre\n" +
                        "4. Exit");
                selection = Integer.parseInt(sc.nextLine());

                switch (selection) {
                    case 1:
                        System.out.println("Please enter the name of the actor you wish to\n" +
                                "get information about, or press enter to return\n" +
                                "to the previous menu");

                        actor = sc.nextLine();
                        //setting statement as string variable, put this back in action when things are working
                        String statement = "SELECT CONCAT(film.film_title,' (',role.role_name,') ') AS film_info,actor.actor_fname,actor.actor_lname FROM pfilms_film AS film, pfilms_role as role, pfilms_actor as actor, pfilms_participates_in as is_in WHERE actor.actor_id = is_in.actor_id AND actor.actor_fname = ? AND is_in.film_id = film.film_id AND is_in.role_id = role.role_id;";

                        //getting the info from actor table foe requested actor
                        try (PreparedStatement pStmt = conn.prepareStatement(statement)) {
                            pStmt.setString(1, actor);

                            //getting the actor details
                            try (ResultSet rs = pStmt.executeQuery()) {
                                rs.next();
                                String actorFn = rs.getString(2);
                                String actorLn = rs.getString(3);
                                System.out.println(actorFn + " " + actorLn + " is listed as being involved in the following films: \n");
                                rs.previous();
                                while (rs.next()) {
                                    String result = rs.getString(1);

                                    System.out.println(result);
                                }

                            } catch (SQLException e) {//end of resultset try block
                                e.printStackTrace();
                            }
                        } catch (SQLException e1) {// End of prepare statement try block
                            e1.printStackTrace();
                        }

                        return;
                    case 2:

                        return;
                    case 3:

                        return;
                    case 4:
                        return;

                }//end of switch
            }//end of while

            } catch (SQLException e) {//end of connection try block
                e.printStackTrace();
            }

        }//end of main method

    }//end of class
