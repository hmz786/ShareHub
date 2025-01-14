package com.portaildepartementinfo.portaildepartementinfo.controller;

import com.portaildepartementinfo.portaildepartementinfo.entities.*;
import com.portaildepartementinfo.portaildepartementinfo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProfesseurController {
    @Autowired
    ProfesseurService professeurService;
    @Autowired
    CoursService coursService;
    @Autowired
    ProjetService projetService;
    @Autowired
    EvaluationService evaluationService;
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("/espaceEP_Professeur/Projets")
    public String afficherProjets(Model model, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        List<Projet> allProjects = projetService.affciherProjet();
        model.addAttribute("allProjects", allProjects);
        return "ProfesseurProjets";
    }

    @GetMapping("/espaceEP_Professeur/Projets/{id}")
    public String getProjetDetails(@PathVariable("id") Integer id, Model model) {
        Projet projet = projetService.afficherProjetById(id);
        List<Evaluation> evaluations = evaluationService.findByProjet(projet);
        model.addAttribute("evaluations", evaluations);
        model.addAttribute("projet", projet);
        return "ProjetDetaille";
    }

    @GetMapping("/espaceEP_Professeur/Cours")
    public String afficherCours(Model model, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        List<Cours> listCours = coursService.findByUser(utilisateur.getId());
        model.addAttribute("listCours", listCours);
        return "ProfesseurCours";
    }

    @GetMapping("/espaceEP_Professeur/Cours/{id}")
    public String getCoursDetails(@PathVariable("id") Integer id, Model model, HttpSession session) {
        Cours cours = coursService.findById(id);
        model.addAttribute("cours", cours);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        List<NotesDeCours> listNotes = professeurService.getNotesDeCoursByCours(cours);
        model.addAttribute("listNotes", listNotes);
        return "CoursDetaille";
    }

    @GetMapping("/espaceEP_Professeur/Notes")
    public String afficherNotes(Model model, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        List<NotesDeCours> listNotes = professeurService.afficherNotes();
        model.addAttribute("listNotes", listNotes);
        return "ProfesseurNotes";
    }
    @GetMapping("/espaceEP_Professeur/NotesByID")
    public String afficherNotesID(Model model, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        List<NotesDeCours> listNotes = professeurService.consulterNotes(utilisateur);
        model.addAttribute("listNotes", listNotes);
        return "ProfesseurNotes";
    }


    @GetMapping("/espaceEP_Professeur/Notes/new")
    public String afficherFormualireNotes(Model model, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");

        NotesDeCours note = new NotesDeCours();

        model.addAttribute("note",note);
        List<Cours> listCours = coursService.findByUser(utilisateur.getId());
        model.addAttribute("listCours", listCours);
        return "addNotesDeCours";
    }

    @PostMapping("/espaceEP_Professeur/Notes/save")
    public String saveNoteDeCours(@ModelAttribute("note") NotesDeCours note,  HttpSession session,
                                  @RequestParam("file") MultipartFile file) {

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        note.setIdUtilisateur(utilisateur);

        if (!file.isEmpty()) {
            try {
                note.setTelecharger(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        professeurService.deposerNotesDeCours(note);
        return "redirect:/espaceEP_Professeur/Notes";
    }


    @GetMapping("/rechercher/NotesProf")
    public String rechercherNoteProf(Model model, @Param("keyword") String keyword)
    {
        List<NotesDeCours> listNotes = professeurService.rechercherNote(keyword);

        model.addAttribute("listNotes",listNotes);
        model.addAttribute("keyword", keyword);
        return "ProfesseurNotes";
    }



    @GetMapping("/espaceEP_Professeur/Notes/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") int id) {
        NotesDeCours note = professeurService.getNoteById(id);

        if (note != null && note.getTelecharger() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + note.getTelecharger() + ".pdf")
                    .body(note.getTelecharger());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }




    @GetMapping("/espaceEP_Professeur/Projets/Evaluation/new")
    public String afficherFormualireEvaluation(Model model, @RequestParam("projetId") Integer projetId){
        Projet projet = projetService.afficherProjetById(projetId);
        model.addAttribute("projet", projet);
        Evaluation eval = new Evaluation();
        model.addAttribute("eval",eval);
        return "addEvaluation";
    }

    @PostMapping("/espaceEP_Professeur/Projets/Evaluation/save")
    public String addEvaluation(@RequestParam("projetId") Integer projetId,
                                @RequestParam("commentaire") String commentaire,
                                @RequestParam("note") String note, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        Projet projet = projetService.afficherProjetById(projetId);
        Evaluation evaluation = new Evaluation();
        evaluation.setCommentaire(commentaire);
        evaluation.setValeurNote(note);
        evaluation.setProjet(projet);
        evaluation.setUser_id(utilisateur);
        evaluationService.save(evaluation);
        return "redirect:/espaceEP_Professeur/Projets";
    }

    @GetMapping("/espaceEP_Professeur/LiensUtiles")
    public String afficherLiens(Model model){
        List<LiensUtiles> listLiens = professeurService.afficherLiens();
        model.addAttribute("listLiens", listLiens);
        return "addLiens";
    }

    @GetMapping("/espaceEP_Professeur/LiensUtiles/New")
    public String ajouterLiens(Model model){
        LiensUtiles liens = new LiensUtiles();
        model.addAttribute("liens", liens);
        return "addLiens";
    }
    @PostMapping("/espaceEP_Professeur/LiensUtiles/Save")
    public String saveLien(LiensUtiles liens, HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        professeurService.deposerLien(liens);
        return "redirect:/espaceEP_Professeur";
    }


}
