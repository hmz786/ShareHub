package com.portaildepartementinfo.portaildepartementinfo.entities;

import jakarta.persistence.*;

@Entity
public class CoursPris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    public CoursPris() {
    }

    public CoursPris(int id, Utilisateur utilisateur, Cours cours) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.cours = cours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    @Override
    public String toString() {
        return "CoursPris{" +
                "id=" + id +
                ", utilisateur=" + utilisateur +
                ", cours=" + cours +
                '}';
    }
}
