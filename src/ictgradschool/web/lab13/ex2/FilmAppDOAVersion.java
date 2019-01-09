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



            menu();


        }

        private static void menu(){
            while(true) {
                try {
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
                            FilmDOA doa=new FilmDOA();
                            List<Actor> allActors = new ArrayList<>();
                            allActors=doa.allActors();
                            query=getQuery("actor");
                            for (Actor actor:allActors
                                 ) {
                                if(actor.getFname().equals(query) || actor.getLname().equals(query)){
                                    List<String> movies=actor.getMovieRoles();
                                    for (String movie:movies
                                         ) {
                                        System.out.println(movie);
                                    }

                                }
                            }
                            break;
                        case 2:
                            query=getQuery("film");
                            break;
                        case 3:
                            query=getQuery("genre");
                            break;
                        case 4:
                            System.exit(0);

                    }
//                return Integer.parseInt(userInput);

                } catch (InvalidEntryException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

        }

        private static String getInput(){
            Scanner sc = new Scanner(System.in);
            String uInput = sc.nextLine();
            return uInput;
        }

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

