package com.portaildepartementinfo.portaildepartementinfo.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class NotesDeCours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotesDeCours;

    @Column(length = 64, nullable = false)
    private String nom;

    @Column(nullable = false)
    private String contenu;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] telecharger;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours idCours;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur idUtilisateur;

    @Column(length = 64, nullable = false)
    private String dateCreation;

    public NotesDeCours() {}

    public NotesDeCours(String nom, String contenu, byte[] telecharger, Cours Cours, Utilisateur idUtilisateur) {
        this.nom = nom;
        this.contenu = contenu;
        this.telecharger = telecharger;
    }

    public int getIdNotesDeCours() {
        return idNotesDeCours;
    }

    public void setIdNotesDeCours(int idNotesDeCours) {
        this.idNotesDeCours = idNotesDeCours;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public byte[] getTelecharger() {
        return telecharger;
    }

    public void setTelecharger(byte[] telecharger) {
        this.telecharger = telecharger;
    }

    public Cours getIdCours() {
        return idCours;
    }

    public void setIdCours(Cours idCours) {
        this.idCours = idCours;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
}
