package com.portaildepartementinfo.portaildepartementinfo.repos;

import com.portaildepartementinfo.portaildepartementinfo.entities.Cours;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository  extends CrudRepository<Cours,Integer> {
    Cours findById(int id);
    @Query(value = "SELECT * FROM Cours WHERE user_id = :userId", nativeQuery = true)
    List<Cours> findByUserId(@Param("userId") int userId);
}

