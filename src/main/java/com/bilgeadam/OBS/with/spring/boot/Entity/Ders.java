package com.bilgeadam.OBS.with.spring.boot.Entity;

public class Ders {
    private long id;
    private long ogretmen_id;
    private long konu_id;

    public Ders() {
    }

    public Ders(long ogretmen_id, long konu_id) {
        this.ogretmen_id = ogretmen_id;
        this.konu_id = konu_id;
    }

    public Ders(long id, long ogretmen_id, long konu_id) {
        this.id = id;
        this.ogretmen_id = ogretmen_id;
        this.konu_id = konu_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOgretmen_id() {
        return ogretmen_id;
    }

    public void setOgretmen_id(long ogretmen_id) {
        this.ogretmen_id = ogretmen_id;
    }

    public long getKonu_id() {
        return konu_id;
    }

    public void setKonu_id(long konu_id) {
        this.konu_id = konu_id;
    }

    @Override
    public String toString() {
        return "Ders{" +
                "id=" + id +
                ", ogretmen_id=" + ogretmen_id +
                ", konu_id=" + konu_id +
                '}';
    }
}
