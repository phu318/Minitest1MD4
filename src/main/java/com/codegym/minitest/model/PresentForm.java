package com.codegym.minitest.model;

import org.springframework.web.multipart.MultipartFile;

public class PresentForm {
    private String name;
    private int price;
    private int id;
    private String code;
    private String ship;
    private  MutipartFile img;
    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public PresentForm(String name, int price, int id, String code, String ship, MutipartFile img) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.code = code;
        this.ship = ship;
        this.img = img;
    }

    public PresentForm() {
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


}
