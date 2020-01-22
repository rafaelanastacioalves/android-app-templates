package com.example.rafaelanastacioalves.moby.entities;


class MainEntity() {

    private lateinit var id: String;
    private lateinit var title: String;
    private lateinit var price: String;
    private lateinit var price_currency: String;
    private lateinit var image_url: String;

    fun  getId(): String {
        return id;
    }

    fun getTitle(): String {
        return title;
    }

    fun getImage_url(): String {
        return image_url;
    }
}
