package com.portaildepartementinfo.portaildepartementinfo.controller;

import com.portaildepartementinfo.portaildepartementinfo.entities.*;
import com.portaildepartementinfo.portaildepartementinfo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class EtudiantController {
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    CoursService coursService;
    @Autowired
    ProjetService projetService;
    @Autowired
    EtudiantService etudiantService;
    @Autowired
    CoursPrisService coursPrisService;
    @Autowired
    DemandeTutoratService demandeTutoratService;
    @Autowired
    CoursTutoredService coursTutoredService;
    @Autowired
    NotificationsService notificationsService;
    @Autowired
    EvaluationService evaluationService;

    @GetMapping("/espaceEP_Etudiant/Projets")
    public String afficherProjets(Model model, HttpSession session) {
        List<Projet> listProjets = projetService.affciherProjet();
        model.addAttribute("listProjets", listProjets);

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        List<Projet> listProjetsEtudiants = projetService.afficherProjetsByUserID(utilisateur);
        model.addAttribute("listProjetsEtudiants", listProjetsEtudiants);
        model.addAttribute("utilisateur", utilisateur);

        return "EtudiantProjets";
    }

    @GetMapping("/espaceEP_Etudiant/Projets/{id}")
    public String getProjetDetails(@PathVariable("id") Integer id, Model model) {
        Projet projet = projetService.afficherProjetById(id);
        List<Evaluation> evaluations = evaluationService.findByProjet(projet);
        model.addAttribute("evaluations", evaluations);
        model.addAttribute("projet", projet);
        return "ProjetDetailleEtudiant";
    }


    @GetMapping("/espaceEP_Etudiant/Cours")
    public String afficherCours(Model model, HttpSession session){
        /*List<Cours> listCours = coursService.afficherCours();
        model.addAttribute("listCours", listCours);*/

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        List<CoursPris> listCoursPris = coursPrisService.getCoursByUtilisateurId(utilisateur.getId());
        model.addAttribute("listCoursPris", listCoursPris);
        model.addAttribute("utilisateur", utilisateur);

        return "EtudiantCours";
    }
    @GetMapping("/espaceEP_Etudiant/Cours/{id}")
    public String getCoursDetails(@PathVariable("id") Integer id, Model model, HttpSession session) {
        Cours cours = coursService.findById(id);
        model.addAttribute("cours", cours);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        List<NotesDeCours> listNotes = etudiantService.afficherNotesDeCoursByCours(cours);
        model.addAttribute("listNotes", listNotes);
        return "CoursDetailleEtudiant";
    }


    @GetMapping("/espaceEP_Etudiant/Notes")
    public String afficherNotes(Model model, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);

        List<NotesDeCours> listNotes = etudiantService.afficherNotes();
        model.addAttribute("listNotes", listNotes);
        return "EtudiantNotes";
    }

    @GetMapping("/espaceEP_Etudiant/Tuteur")
    public String afficherTutorats(Model model, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);

        List<CoursPris> listCoursPris = coursPrisService.getCoursByUtilisateurId(utilisateur.getId());
        model.addAttribute("listCoursPris", listCoursPris);

        List<DemandeTutorat> listDemandes = demandeTutoratService.findAll();
        model.addAttribute("listDemandes", listDemandes);
        return "EtudiantTuteur";
    }

    @PostMapping("/espaceEP_Etudiant/Tuteur/save")
    public String saveTuteur(HttpSession session, @RequestParam("cours") int coursId) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        utilisateur.setTuteur(true);

        utilisateurService.enregistrerUtilisateur(utilisateur);

        Cours cours = coursService.findById(coursId);
        CoursTutored coursTutored = new CoursTutored();
        coursTutored.setUtilisateur(utilisateur);
        coursTutored.setCours(cours);

        coursTutoredService.save(coursTutored);

        return "redirect:/espaceEP_Etudiant/Tutorat";
    }

    @GetMapping("/espaceEP_Etudiant/Tutorat")
    public String afficherTuteur(Model model, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("estTuteur", utilisateur.isTuteur());

        List<Cours> listCours = coursService.afficherCours();
        model.addAttribute("listCours", listCours);

        List<CoursPris> listCoursPris = coursPrisService.getCoursByUtilisateurId(utilisateur.getId());
        model.addAttribute("listCoursPris", listCoursPris);

        List<Notifications> notifications = notificationsService.getNotificationsByUtilisateur(utilisateur);
        model.addAttribute("notifications", notifications);

        DemandeTutorat demande = new DemandeTutorat();
        model.addAttribute("demande", demande);

        return "EtudiantTutorat";
    }

    @PostMapping("/espaceEP_Etudiant/Tutorat/save")
    public String saveDemande(@ModelAttribute DemandeTutorat demande, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        demande.setIdUtilisateur(utilisateur);

        demandeTutoratService.enregistrerDemande(demande);

        return "redirect:/espaceEP_Etudiant/Tutorat";
    }

    @PostMapping("/espaceEP_Etudiant/Tutorat/Update")
    public String updateStatutDemande(@RequestParam("demandeId") int demandeId, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        DemandeTutorat demande = demandeTutoratService.findById(demandeId);

        demande.setTuteur(utilisateur.getNom());
        demande.setStatut(true);
        demandeTutoratService.enregistrerDemande(demande);

        Utilisateur proprietaire = demande.getIdUtilisateur();
        String message = "Votre demande de tutorat a été prise en charge par " + utilisateur.getNom() + ".";
        notificationsService.addNotification(message, proprietaire);

        return "redirect:/espaceEP_Etudiant/Tutorat";
    }


}
