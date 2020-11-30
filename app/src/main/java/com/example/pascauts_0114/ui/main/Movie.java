package com.example.pascauts_0114.ui.main;

public class Movie {
    private String title;
    private String year;
    private String image;
    private String overview;

    public Movie(String title, String year, String image, String overview) {
        this.title = title;
        this.year = year;
        this.image = image;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
