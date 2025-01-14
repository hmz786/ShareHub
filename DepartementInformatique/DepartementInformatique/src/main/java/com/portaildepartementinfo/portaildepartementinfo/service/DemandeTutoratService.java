package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.DemandeTutorat;
import com.portaildepartementinfo.portaildepartementinfo.repos.DemandeTutoratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeTutoratService {
    @Autowired
    DemandeTutoratRepository repos;

    public void enregistrerDemande(DemandeTutorat demande){
        repos.save(demande);
    }

    public List<DemandeTutorat> findAll(){
        return (List<DemandeTutorat>) repos.findAll();
    }

    public DemandeTutorat findById(int id) {
        return repos.findByIdTuteur(id);
    }
}
