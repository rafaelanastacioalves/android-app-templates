package com.example.rafaelanastacioalves.moby.vo;


public class MainEntity {

    private String id;
    private String title;
    private String price;
    private String price_currency;
    private String image_url;

    public MainEntity(){
        super();
    }

    public MainEntity(String title) {
        id = "123";
        title = title;
        price = "R$10,00";
        price_currency = "R$10,00";
        image_url = "http://xxx.com";
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
}
