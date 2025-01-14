package com.portaildepartementinfo.portaildepartementinfo.entities;

import jakarta.persistence.*;

@Entity
public class LiensUtiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 140)
    private String lien;

    public LiensUtiles() {
    }

    public LiensUtiles(String lien) {
        this.id = id;
        this.lien = lien;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
