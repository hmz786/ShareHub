package com.portaildepartementinfo.portaildepartementinfo.entities;


import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hamza
 */
@Entity
@Table(name = "projets")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjet;

    @ManyToOne
    @JoinColumn(name = "Cours_Id")
    private Cours idCours;
    @OneToOne
    @JoinColumn(name = "Evaluation_Id")
    private Evaluation idEvaluation;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private Utilisateur idUtilisateur;

    @Column(name = "NomProjet",nullable = false,length = 255)
    private String nom;
    @Column(length = 255, nullable = false)
    private String video;
    @Column(length = 255)
    private String description;
    @Column(length = 255)
    private String AnneeSession;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] telecharger;

    @Column(length = 255)
    private String urlProjetGit;

    public Projet() {
    }

    public Projet(Integer idProjet, Cours idCours, Evaluation idEvaluation, Utilisateur idUtilisateur, String nom, String video, String description, String anneeSession, byte[] telecharger, String urlProjetGit) {
        this.idProjet = idProjet;
        this.idCours = idCours;
        this.idEvaluation = idEvaluation;
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.video = video;
        this.description = description;
        AnneeSession = anneeSession;
        this.telecharger = telecharger;
        this.urlProjetGit = urlProjetGit;
    }

    public Integer getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Integer idProjet) {
        this.idProjet = idProjet;
    }

    public Cours getIdCours() {
        return idCours;
    }

    public void setIdCours(Cours idCours) {
        this.idCours = idCours;
    }

    public Evaluation getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Evaluation idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Utilisateur getUser_id() {
        return idUtilisateur;
    }

    public void setUser_id(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnneeSession() {
        return AnneeSession;
    }

    public void setAnneeSession(String anneeSession) {
        AnneeSession = anneeSession;
    }

    public byte[] getTelecharger() {
        return telecharger;
    }

    public void setTelecharger(byte[] telecharger) {
        this.telecharger = telecharger;
    }

    public String getUrlProjetGit() {
        return urlProjetGit;
    }

    public void setUrlProjetGit(String urlProjetGit) {
        this.urlProjetGit = urlProjetGit;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "idProjet=" + idProjet +
                ", idCours=" + idCours +
                ", idEvaluation=" + idEvaluation +
                ", user_id=" + idUtilisateur +
                ", nom='" + nom + '\'' +
                ", video='" + video + '\'' +
                ", description='" + description + '\'' +
                ", AnneeSession='" + AnneeSession + '\'' +
                ", telecharger=" + Arrays.toString(telecharger) +
                ", urlProjetGit='" + urlProjetGit + '\'' +
                '}';
    }
}

