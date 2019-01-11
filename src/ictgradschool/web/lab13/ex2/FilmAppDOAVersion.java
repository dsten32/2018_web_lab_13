package ictgradschool.web.lab13.ex2;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmAppDOAVersion {


        private static Boolean keepEntering;
        private static String userInput;
        private static int selection;
        private static String query;

        public static void main(String[] args){
            userInput = "";
            query ="";
            //call the menu method
            menu();
        }

        private static void menu(){
            while(true) {
                try {
                    //main menu stuff
                    System.out.println("Welcome to the Film database!\n" +
                            "Please select an option from the following:\n" +
                            "1. Information by Actor\n" +
                            "2. Information by Movie\n" +
                            "3. Information by Genre\n" +
                            "4. Exit");
                    userInput = getInput();
                    checkSelection(userInput);

                    switch (Integer.parseInt(userInput)){
                        case 1:
                            FilmDOA doaA=new FilmDOA();
                            List<Actor> allActors = new ArrayList<>();
                            allActors=doaA.allActors();
                            query=getQuery("actor");

                            for (Actor actor:allActors
                                 ) {
                                if(actor.getFname().equalsIgnoreCase(query) || actor.getLname().equalsIgnoreCase(query)){
                                    System.out.println("\n"+actor.getFname()+" "+actor.getLname()+" is listed as being involved in the following films: \n");
                                    List<String> movies=actor.getMovieRoles();
                                    for (String movie:movies
                                         ) {
                                        System.out.println(movie);
                                    }
                                    System.out.println("\n");
                                }
                            }//end of foreach
                            getQuery("actor");
//                            break;
                        case 2:
                            FilmDOA doaM=new FilmDOA();
                            List<Movie> allMovies = new ArrayList<>();
                            allMovies=doaM.allMovies();
                            query=getQuery("film");

                            for (Movie movie:allMovies
                            ) {
                                if(movie.getTitle().equalsIgnoreCase(query)){
                                    System.out.println("\nThe film "+movie.getTitle()+" is a "+movie.getGenre()+" movie that features the following people: \n");
                                    List<String> actors=movie.getActorRoles();
                                    for (String actor:actors
                                    ) {
                                        System.out.println(actor);
                                    }
                                    System.out.println("\n");
                                }
                            }//end of foreach

                            query=getQuery("film");
                            break;
                        case 3:
                            FilmDOA doaG=new FilmDOA();
                            List<Genre> allGenres = new ArrayList<>();
                            allGenres=doaG.allGenres();
                            query=getQuery("genre");

                            for (Genre genre:allGenres
                            ) {
                                if(genre.getGenre().equalsIgnoreCase(query)){
                                    System.out.println("\nThe "+genre.getGenre()+" includes the following films: \n");
                                    List<String> movies=genre.getMovies();
                                    for (String movie:movies
                                    ) {
                                        System.out.println(movie);
                                    }
                                    System.out.println("\n");
                                }
                            }//end of foreach
                            query=getQuery("genre");
                            break;
                        case 4:
                            System.exit(0);

                    }//end of switch block
//                return Integer.parseInt(userInput);

                } catch (InvalidEntryException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

        }

        //method for creating a scanner and returning user input
        private static String getInput(){
            Scanner sc = new Scanner(System.in);
            String uInput = sc.nextLine();
            return uInput;
        }
        //checks user input is appropriate for the main menu option selection.
        private static void checkSelection(String uInput) throws InvalidEntryException{
            try {
                int userNum = Integer.parseInt(uInput);
                if (userNum < 1 || userNum > 4) {
                    throw new InvalidEntryException("Entry must be a valid integer 1-4");
                }
            } catch (NumberFormatException e){
                throw new InvalidEntryException("Entry must be a valid integer 1-4");
            }

        }
        //method to pass in the type of query the user wants to run get the
        // query parameter they want and pass back
        private static String getQuery(String type){

            System.out.println("Please enter the name of the "+type+" you wish to\n" +
                    "get information about, or press enter to return\n" +
                    "to the previous menu");

            query=getInput();
            if(query.length()==0){
                menu();
            } else {
                return query;
            }
            return null;
        }

    }

