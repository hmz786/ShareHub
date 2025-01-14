package com.portaildepartementinfo.portaildepartementinfo.repos;

import com.portaildepartementinfo.portaildepartementinfo.entities.CoursPris;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursPrisRepository extends CrudRepository<CoursPris,Integer> {
    List<CoursPris> findByUtilisateurId(int utilisateurId);
}
