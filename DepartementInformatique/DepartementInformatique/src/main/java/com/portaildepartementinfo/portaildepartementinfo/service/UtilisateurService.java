package com.portaildepartementinfo.portaildepartementinfo.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.portaildepartementinfo.portaildepartementinfo.entities.AccountType;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import com.portaildepartementinfo.portaildepartementinfo.repos.RoleRepository;
import com.portaildepartementinfo.portaildepartementinfo.repos.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    RoleRepository roleRepos;
    @Autowired
    UtilisateurRepository repo;
    private static final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);
    public List<Utilisateur> afficherUtilisateurs(){
        return ( List<Utilisateur>)  repo.findAll();
    }

    public List<AccountType> afficherRoles(){

        return ( List<AccountType>)  roleRepos.findAll();
    }

    public void enregistrerUtilisateur(Utilisateur utilisateur) {
        logger.info("Enregistrement de l'utilisateur : {}", utilisateur);

        AccountType accountType = roleRepos.findById(utilisateur.getAccountType().getId()).orElse(null);
        utilisateur.setAccountType(accountType);

        repo.save(utilisateur);

        logger.info("Utilisateur enregistré avec succès");
    }

    public Utilisateur validateUser(String email, String password) {
        Utilisateur user = repo.findByEmail(email);
        if (user != null && password.equals(user.getMotDePasse())) {
            return user;
        }
        return null;
    }

    public Utilisateur findUtilisateurById(int id){
        Optional<Utilisateur> utilisateurOptional = repo.findById(id);
        return utilisateurOptional.orElse(null);
    }

    public Utilisateur trouverParEmail(String email){
        return repo.findByEmail(email);
    }




}
