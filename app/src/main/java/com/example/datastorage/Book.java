package com.example.datastorage;

public class Book {
    private String name;
    private String price;
    private String category;

    public Book(String name, String price, String category){

        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

}
