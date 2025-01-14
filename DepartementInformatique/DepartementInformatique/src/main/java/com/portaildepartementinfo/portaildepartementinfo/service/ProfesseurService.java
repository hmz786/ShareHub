package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.*;
import com.portaildepartementinfo.portaildepartementinfo.repos.EvaluationRepository;
import com.portaildepartementinfo.portaildepartementinfo.repos.LiensUtilesRepository;
import com.portaildepartementinfo.portaildepartementinfo.repos.NotesDeCoursRepository;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    NotesDeCoursRepository notesDeCoursRepository;
    @Autowired
    LiensUtilesRepository liensUtilesRepository;

    public void evaluerProjet(Utilisateur userId, Evaluation evaluation) {
        evaluation.setUser_id(userId);
        evaluationRepository.save(evaluation);
    }

    public List<NotesDeCours> afficherNotes(){
        return notesDeCoursRepository.findAll();
    }


    public void deposerNotesDeCours(NotesDeCours note) {

        notesDeCoursRepository.save(note);
    }


     public void deposerLien(LiensUtiles lien) {

        liensUtilesRepository.save(lien);
    }



         public List<NotesDeCours> consulterNotes(Utilisateur idProfesseur) {
      return notesDeCoursRepository.findByIdUtilisateur(idProfesseur);
    }

    public  List<LiensUtiles> afficherLiens(){
        return liensUtilesRepository.findAll();
    }


        public List<NotesDeCours> rechercherNote(String keyword) {

            if (keyword != null) {
                return notesDeCoursRepository.findAll(keyword);
            }

            return  null;
        }

    public NotesDeCours getNoteById(int id) {
        return notesDeCoursRepository.findById(id).orElse(null);

    }

    public List<NotesDeCours> getNotesDeCoursByCours(Cours cours) {
        return notesDeCoursRepository.findByIdCours(cours);
    }

}
