package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.Cours;
import com.portaildepartementinfo.portaildepartementinfo.entities.NotesDeCours;
import com.portaildepartementinfo.portaildepartementinfo.repos.NotesDeCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    NotesDeCoursRepository notesDeCoursRepository;

    public List<NotesDeCours> afficherNotes(){
        return notesDeCoursRepository.findAll();
    }

    public List<NotesDeCours> afficherNotesDeCoursByCours(Cours cours){
        return notesDeCoursRepository.findByIdCours(cours);
    }
}
