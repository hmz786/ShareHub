package com.portaildepartementinfo.portaildepartementinfo.entities;

import jakarta.persistence.*;

@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Utilisateur personne1;

    @OneToOne
    private Utilisateur personne2;
}
