package com.bilgeadam.OBS.with.spring.boot.Entity;

public class Konu {


    private long id;
    private String name;

    public Konu() {
    }

    public Konu(String name) {
        this.name = name;
    }

    public Konu(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Konu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "}\n";
    }
}