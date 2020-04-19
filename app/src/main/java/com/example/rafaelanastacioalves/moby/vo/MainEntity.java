package com.example.rafaelanastacioalves.moby.vo;


public class MainEntity {

    private String id;
    private String title;
    private String price;
    private String price_currency;
    private String image_url;

    protected static final String defaultId = "123";
    protected static final String defaultPrice = "R$10,00";
    protected static final String defaultUrlDeclaration = "http://xxx.com";

    public MainEntity(){
        super();
    }

    public MainEntity(String title) {
        id = defaultId;
        this.title = title;
        price = defaultPrice;
        price_currency = defaultPrice;
        image_url = defaultUrlDeclaration;
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
