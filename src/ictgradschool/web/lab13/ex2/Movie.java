package ictgradschool.web.lab13.ex2;

public class Movie {
    private int id;
    private String title,genre;

    public Movie(int id, String title, String genre){
        this.id = id;
        this.genre = genre;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
