package ictgradschool.web.lab13.ex2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FilmDOA {
@SuppressWarnings("Duplicates")
public List<Actor> allActors(){
    List<Actor> actors = new ArrayList<>();
    String actorName="";

    //set up properties object and load file containing db details, username and password
    Properties dbProps = new Properties();
    try (FileInputStream fIn = new FileInputStream("mysql.properties")) {
        dbProps.load(fIn);
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Etablishing connection to db using try w/resources to ensure auto close,
    // and using properties object to supply the parameters
    try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
        System.out.println("Connection successful");

        //string variable holding the query for all the things we want to know about actors
        String actorStatement = "SELECT CONCAT(film.film_title,' (',role.role_name,') ') AS film_info,actor.actor_fname,actor.actor_lname FROM pfilms_film AS film, pfilms_role as role, pfilms_actor as actor, pfilms_participates_in as is_in WHERE actor.actor_id = is_in.actor_id AND actor.actor_fname = ? AND is_in.film_id = film.film_id AND is_in.role_id = role.role_id;";

        //getting the info from actor table foe requested actor
        try (PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM pfilms_actor")) {
//            pStmt.setString(1, actorName);

            //getting the actor details
            try (ResultSet rs = pStmt.executeQuery()) {
                //get column numbers and set as variables
                int fnCol = rs.findColumn("actor_fname");
                int lnCol = rs.findColumn("actor_lname");
                int idCol = rs.findColumn("actor_id");
//                System.out.println(actorFn + " " + actorLn + " is listed as being involved in the following films: \n");

                while (rs.next()) {
                    Actor actor = new Actor();

                    actor.setFname(rs.getString(fnCol));
                    actor.setLname(rs.getString(lnCol));
                    actor.setId(rs.getInt(idCol));

                    actors.add(actor);
                }

                //populate the actors movie roles
                for (Actor actor:actors
                     ) {
                    try(PreparedStatement mStmt = conn.prepareStatement("SELECT CONCAT(film.film_title,' (',role.role_name,')') AS film_info FROM pfilms_film AS film, pfilms_role as role, pfilms_actor as actor, pfilms_participates_in as is_in WHERE actor.actor_fname = ? AND actor.actor_id = is_in.actor_id AND is_in.role_id = role.role_id AND is_in.film_id = film.film_id;")){
                        mStmt.setString(1,actor.getFname());

                        try (ResultSet mrs = mStmt.executeQuery()) {
                            List<String> movieRoles=new ArrayList<>();
                            while(mrs.next()){
                                movieRoles.add(mrs.getString(1));
                            }
                            actor.setMovieRoles(movieRoles);

                        } catch (SQLException e){
                            e.printStackTrace();
                        }

                        //loop through results and add to a string list that becomes the actors movie role list


                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }




            } catch (SQLException e) {//end of resultset try block
                e.printStackTrace();
            }
        } catch (SQLException e1) {// End of prepare statement try block
            e1.printStackTrace();
        }

    }catch (SQLException e) {//end of connection try block
        e.printStackTrace();
    }

    return actors;
    }

}
