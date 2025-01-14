package com.portaildepartementinfo.portaildepartementinfo.repos;

import com.portaildepartementinfo.portaildepartementinfo.entities.Projet;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Integer> {
    List<Projet> findProjetByIdUtilisateur(Utilisateur userId);
    @Query("SELECT p FROM Projet p WHERE p.nom  LIKE %?1% ")
    public List<Projet> findAll(String keyword);

    Projet findByIdProjet(int idProjet);
}
