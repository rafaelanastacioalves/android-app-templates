package com.example.rafaelanastacioalves.moby.domain.entities;


public class MainEntity {

    private String id;
    private String title;
    private String price;
    private String price_currency;
    private String image_url;

    public MainEntity(){
        super();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getPrice() {
        return price;
    }
}
