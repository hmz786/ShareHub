package com.portaildepartementinfo.portaildepartementinfo.repos;

import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur,Integer> {



    public Utilisateur findByEmail(String email);


    public Utilisateur findByPrenom(String prenom);

}
