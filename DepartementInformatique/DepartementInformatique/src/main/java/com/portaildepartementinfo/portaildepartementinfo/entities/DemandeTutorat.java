package com.portaildepartementinfo.portaildepartementinfo.entities;

import jakarta.persistence.*;
import jdk.jshell.execution.Util;

/**
 *
 * @author hamza
 */
@Entity
@Table()
public class DemandeTutorat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTuteur;
    @ManyToOne
    private Utilisateur IdUtilisateur;
    @ManyToOne
    private Cours coursDemande;
    @Column(nullable = false)
    private String difficultes;
    @Column(length =64, nullable = false)
    private String typeRencontre;
    @Column(length =64, nullable = false)
    private String disponibilites;
    @Column(length =64)
    private boolean Statut;

    @Column(name = "nomTuteur")
    private String tuteur;


    // Constructeur

    public DemandeTutorat() {
    }

    public DemandeTutorat(int idTuteur, Utilisateur idUtilisateur, Cours coursDemande, String difficultes, String typeRencontre, String disponibilites, boolean statut, String tuteur) {
        idTuteur = idTuteur;
        IdUtilisateur = idUtilisateur;
        this.coursDemande = coursDemande;
        this.difficultes = difficultes;
        this.typeRencontre = typeRencontre;
        this.disponibilites = disponibilites;
        Statut = statut;
        this.tuteur = tuteur;
    }

    public int getIdTuteur() {
        return idTuteur;
    }

    public void setIdTuteur(int idTuteur) {
        idTuteur = idTuteur;
    }

    public Cours getCoursDemande() {
        return coursDemande;
    }

    public void setCoursDemande(Cours coursDemande) {
        this.coursDemande = coursDemande;
    }

    public String getTypeRencontre() {
        return typeRencontre;
    }

    public void setTypeRencontre(String typeRencontre) {
        this.typeRencontre = typeRencontre;
    }

    public String getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(String disponibilites) {
        this.disponibilites = disponibilites;
    }

    public boolean isStatut() {
        return Statut;
    }

    public void setStatut(boolean statut) {
        Statut = statut;
    }

    public String getTuteur() {
        return tuteur;
    }

    public void setTuteur(String tuteur) {
        this.tuteur = tuteur;
    }

    @Override
    public String toString() {
        return "DemandeTutorat{" +
                "IdTuteur=" + idTuteur +
                ", coursDemande='" + coursDemande + '\'' +
                ", typeRencontre='" + typeRencontre + '\'' +
                ", disponibilites='" + disponibilites + '\'' +
                ", Statut=" + Statut +
                ", tuteur='" + tuteur + '\'' +
                '}';
    }

    public String getDifficultes() {
        return difficultes;
    }

    public void setDifficultes(String difficultes) {
        this.difficultes = difficultes;
    }

    public Utilisateur getIdUtilisateur() {
        return IdUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        IdUtilisateur = idUtilisateur;
    }
}