package com.portaildepartementinfo.portaildepartementinfo.repos;

import com.portaildepartementinfo.portaildepartementinfo.entities.DemandeTutorat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeTutoratRepository extends CrudRepository<DemandeTutorat, Integer> {
    DemandeTutorat findByIdTuteur(int id);
}
