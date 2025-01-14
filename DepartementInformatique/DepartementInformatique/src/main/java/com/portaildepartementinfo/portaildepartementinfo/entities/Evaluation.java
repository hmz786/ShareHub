package com.portaildepartementinfo.portaildepartementinfo.entities;

import jakarta.persistence.*;

/**
 *
 * @author hamza
 */
@Entity
@Table()
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvaluation;
    @Column(length =255, nullable = false)
    private String Commentaire;
    @Column(length =64, nullable = false)
    private String valeurNote;
    @ManyToOne
    @JoinColumn(name ="user_id")
    private Utilisateur user_id;
    @OneToOne
    private Projet projet;

    public Evaluation() {
    }

    public Evaluation(int idEvaluation, String commentaire, String valeurNote, Utilisateur user_id, Projet projet) {
        this.idEvaluation = idEvaluation;
        Commentaire = commentaire;
        this.valeurNote = valeurNote;
        this.user_id = user_id;
        this.projet = projet;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String commentaire) {
        Commentaire = commentaire;
    }

    public String getValeurNote() {
        return valeurNote;
    }

    public void setValeurNote(String valeurNote) {
        this.valeurNote = valeurNote;
    }

    public Utilisateur getUser_id() {
        return user_id;
    }

    public void setUser_id(Utilisateur user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "idEvaluation=" + idEvaluation +
                ", Commentaire='" + Commentaire + '\'' +
                ", valeurNote='" + valeurNote + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
