package com.example.zomatoclone.Model;

public class RestaurantItem {
    private int image;
    private String rating;
    private String title;
    private String subtitle;
    private String price;
    private String time;

    public RestaurantItem(int image, String rating, String title, String subtitle, String price, String time) {
        this.image = image;
        this.rating = rating;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.time = time;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
