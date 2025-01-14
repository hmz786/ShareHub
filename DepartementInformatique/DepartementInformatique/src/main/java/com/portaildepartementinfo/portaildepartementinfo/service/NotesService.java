package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.Cours;
import com.portaildepartementinfo.portaildepartementinfo.entities.NotesDeCours;
import com.portaildepartementinfo.portaildepartementinfo.repos.NotesDeCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotesService {

    @Autowired
    NotesDeCoursRepository repos;

    public List<NotesDeCours> afficherNotes(){

        return (List<NotesDeCours>) repos.findAll();
    }
    public NotesDeCours enregistrerNoteDeCours(NotesDeCours noteDeCours) {
        return repos.save(noteDeCours);
    }

    public void supprimerUtilisateur( )  {
    }

    public void supprimerNoteDeCours(int idNoteCours) {
        repos.deleteById(idNoteCours);
    }

    public void rechercherNote(int idNoteCours) {
        repos.deleteById(idNoteCours);
    }

}



