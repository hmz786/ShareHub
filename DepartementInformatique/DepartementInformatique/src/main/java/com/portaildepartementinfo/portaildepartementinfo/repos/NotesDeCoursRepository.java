package com.portaildepartementinfo.portaildepartementinfo.repos;

import com.portaildepartementinfo.portaildepartementinfo.entities.Cours;
import com.portaildepartementinfo.portaildepartementinfo.entities.NotesDeCours;
import com.portaildepartementinfo.portaildepartementinfo.entities.Projet;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesDeCoursRepository extends JpaRepository<NotesDeCours, Integer> {

    List<NotesDeCours> findByIdUtilisateur(Utilisateur idUtilisateur);

    List<NotesDeCours> findByIdCours(Cours idCours);

    @Query("SELECT n FROM NotesDeCours n WHERE n.nom  LIKE %?1% ")
    public List<NotesDeCours> findAll(String keyword);
}

