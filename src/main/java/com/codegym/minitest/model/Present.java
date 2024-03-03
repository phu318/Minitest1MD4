package com.codegym.minitest.model;

import javax.persistence.*;

@Entity
@Table(name = "present")
public class Present {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private int price;
    private int id;
    private String code;
    private String ship;
    private  String img;

    public Present() {
    }

    public Present(String name, int price, int id, String code, String ship, String img) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.code = code;
        this.ship = ship;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
