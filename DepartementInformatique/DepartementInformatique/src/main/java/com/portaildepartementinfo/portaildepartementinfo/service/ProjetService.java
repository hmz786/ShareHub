package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.Projet;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import com.portaildepartementinfo.portaildepartementinfo.repos.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProjetService {

    @Autowired
    ProjetRepository repos;

    private final ResourceLoader resourceLoader;

    @Autowired
    public ProjetService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Projet> affciherProjet(){ return (List<Projet>) repos.findAll();}

    public Projet enregistrer(Projet projet){ return repos.save(projet);}


    public List<Projet> afficherProjetsByUserID(Utilisateur userId) {
        return (List<Projet>) repos.findProjetByIdUtilisateur(userId);
    }

    public Projet afficherProjetById(int id) {
        return repos.findByIdProjet(id);
    }





    public Resource load(String filename) {
        try {
            Resource resource = resourceLoader.getResource("classpath:/projets/" + filename);

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Projet uploadImage(MultipartFile file) throws IOException {
        Projet fichier = new Projet();
        fichier.setNom(file.getOriginalFilename());
        fichier.setTelecharger(file.getBytes());
        return repos.save(fichier);
    }


    public List<Projet> rechercherProjet(String keyword) {

        if (keyword != null) {
            return repos.findAll(keyword);
        }

        return  null;
    }
}
