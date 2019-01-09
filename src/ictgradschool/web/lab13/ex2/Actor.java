package ictgradschool.web.lab13.ex2;

public class Actor {
    private int id;
    private String fname,lname;

    public Actor(int id, String fname, String lname){
        this.fname = fname;
        this.lname = lname;
        this.id = id;
    }

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

}
