package ictgradschool.web.lab13.ex2;

import java.util.List;

public class Actor {
    private int id;
    private String fname,lname;
    private List<String> movieRoles;

//    public Actor(int id, String fname, String lname){
//        this.fname = fname;
//        this.lname = lname;
//        this.id = id;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<String> getMovieRoles() {
        return movieRoles;
    }

    public void setMovieRoles(List<String> movieRoles) {
        this.movieRoles = movieRoles;
    }
}
