package com.example.sell_book.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product extends  BaseEntity{
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "so_luong")
    private int so_luong;

    @Column(name = "price")
    private int price;

    @Column(name = "active")
    private boolean active;

    @Column(name = "url_image")
    private String url_image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

}
