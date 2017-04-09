package com.natura.teste.thaissantos.makeitup;

/**
 * Created by thais.santos on 09/04/2017.
 */

public class Product {

    private String id;
    private String name;
    private int color_r;
    private int color_g;
    private int color_b;
    private Category category;
    private String url_image;

    public Product(String id, String name, int color_r, int color_g, int color_b, Category category, String url_image) {
        this.id = id;
        this.name = name;
        this.color_r = color_r;
        this.color_g = color_g;
        this.color_b = color_b;
        this.category = category;
        this.url_image = url_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor_r() {
        return color_r;
    }

    public void setColor_r(int color_r) {
        this.color_r = color_r;
    }

    public int getColor_g() {
        return color_g;
    }

    public void setColor_g(int color_g) {
        this.color_g = color_g;
    }

    public int getColor_b() {
        return color_b;
    }

    public void setColor_b(int color_b) {
        this.color_b = color_b;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
