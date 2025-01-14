package com.portaildepartementinfo.portaildepartementinfo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hamza
 */
@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCours;
    @Column(length = 64, nullable = false)
    private String nom;
    @Column
    private int numero;
    @Column(length = 150, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private Utilisateur user_id;

    @Column(length = 64)
    private String salle;
    @Column(length = 64)
    private String horaire;
    @Column
    private int credits;

    public Cours() {
    }

    public Cours(int idCours, String nom, int numero, String description, Utilisateur user_id, String salle, String horaire, int credits) {
        this.idCours = idCours;
        this.nom = nom;
        this.numero = numero;
        this.description = description;
        this.user_id = user_id;
        this.salle = salle;
        this.horaire = horaire;
        this.credits = credits;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Utilisateur getUser_id() {
        return user_id;
    }

    public void setUser_id(Utilisateur user_id) {
        this.user_id = user_id;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "idCours=" + idCours +
                ", nom='" + nom + '\'' +
                ", numero=" + numero +
                ", description='" + description + '\'' +
                ", user_id=" + user_id +
                ", salle='" + salle + '\'' +
                ", horaire='" + horaire + '\'' +
                ", credits=" + credits +
                '}';
    }
}