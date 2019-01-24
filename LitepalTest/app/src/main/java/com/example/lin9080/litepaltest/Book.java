package com.example.lin9080.litepaltest;

import org.litepal.crud.DataSupport;

/**
 * Created by 90806 on 2019/1/24.
 */

public class Book extends DataSupport{
    private int id;
    private String author;
    private int pages;
    private double price;
    private String name;

    @Override
    public String toString() {
        return getId()+","+getName()+","+getPages()+","+getPrice()+","+getAuthor()+";";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
