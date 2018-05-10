package com.example.rafaelanastacioalves.moby.entities;


class EntityDetails {

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var price: String
    private lateinit var image_url: String


    fun getTitle(): String {
        return title;
    }

    fun getDescription(): String {
        return description;
    }

    fun getPrice(): String {
        return price;
    }

    fun getImage_url(): String {
        return image_url;
    }
}
