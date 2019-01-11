package ictgradschool.web.lab13.ex2;

import java.util.List;

public class Genre {
    private String genre;
    List<String> movies;

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
