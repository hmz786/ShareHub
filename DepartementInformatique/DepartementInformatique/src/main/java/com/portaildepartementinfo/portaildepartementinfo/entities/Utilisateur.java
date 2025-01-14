package com.portaildepartementinfo.portaildepartementinfo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hamza
 */
@Entity
@Table(name = "utilisateurs")
public class Utilisateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "nom",length = 64, nullable = false)
    private String nom;
    @Column(name = "prenom",length = 64, nullable = false)
    private String prenom;

    @Column(length = 64, nullable = false, unique = true)
    private String email;
    @Column(length = 64, nullable = false)
    private String motDePasse;

    @Column(name = "tuteur")
    private boolean tuteur;

    @ManyToOne
    @JoinColumn(name = "accounttype_id")
    private AccountType accountType;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, boolean tuteur, AccountType accountType) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.tuteur = tuteur;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isTuteur() {
        return tuteur;
    }

    public void setTuteur(boolean tuteur) {
        this.tuteur = tuteur;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", tuteur=" + tuteur +
                ", accountType=" + accountType +
                '}';
    }
}
