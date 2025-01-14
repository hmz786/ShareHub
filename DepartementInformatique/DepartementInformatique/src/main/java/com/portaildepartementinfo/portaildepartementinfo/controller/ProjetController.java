package com.portaildepartementinfo.portaildepartementinfo.controller;

import com.portaildepartementinfo.portaildepartementinfo.entities.AccountType;
import com.portaildepartementinfo.portaildepartementinfo.entities.Cours;
import com.portaildepartementinfo.portaildepartementinfo.entities.Projet;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import com.portaildepartementinfo.portaildepartementinfo.service.CoursService;
import com.portaildepartementinfo.portaildepartementinfo.service.ProjetNotFoundException;
import com.portaildepartementinfo.portaildepartementinfo.service.ProjetService;
import com.portaildepartementinfo.portaildepartementinfo.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class ProjetController {
    @Autowired
    ProjetService service;
    @Autowired
    UtilisateurService serviceUti;
    @Autowired
    CoursService coursService;
    @GetMapping("/projet")
    public String afficherProjet(Model model){
        List<Projet> listProjet = service.affciherProjet();
        model.addAttribute("listProjet",listProjet);
        return "pageProjets";
    }


    @GetMapping("/projet/ajouterPojet")
    public String ajouterProjet(Model model, Principal principal){
        List<Projet> listProjetEtudiant = service.affciherProjet();
        model.addAttribute("listProjetEtudiant",listProjetEtudiant);
        Projet projet = new Projet();
        model.addAttribute("projet",projet);
        List<Cours> listCours = coursService.afficherCours();
        model.addAttribute("listCours", listCours);
        return "addproject";
    }





    @GetMapping("/EtudiantProjets/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = service.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/ajouter/projet/save")
    public String ajouterProjetEtudiant( @ModelAttribute("projet") Projet projet,
                                         @RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes,HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        projet.setUser_id(utilisateur);

        if (!file.isEmpty()) {
            try {
                projet.setTelecharger(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        service.enregistrer(projet);
        redirectAttributes.addFlashAttribute("message", "votre projet :  " + projet.getNom() + " a été  ajouté avec succès.");
        return "redirect:/espaceEP_Etudiant/Projets";
    }



    @GetMapping("/rechercher/projet")
    public String rechercherProjet(Model model, @Param("keyword") String keyword)
    {
        List<Projet> listProjetsEtudiants = service.rechercherProjet(keyword);

        model.addAttribute("listProjetsEtudiants",listProjetsEtudiants);
        model.addAttribute("keyword", keyword);
        return "EtudiantProjets";
    }

}
