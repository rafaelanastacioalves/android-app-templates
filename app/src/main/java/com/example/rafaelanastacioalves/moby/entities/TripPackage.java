package com.example.rafaelanastacioalves.moby.entities;


public class TripPackage {

    private String id;
    private String title;
    private String price;
    private String price_currency;
    private String image_url;

    public TripPackage(){
        super();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getPrice_currency() {
        return price_currency;
    }

    public String getImage_url() {
        return image_url;
    }
}
