package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.Cours;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import com.portaildepartementinfo.portaildepartementinfo.repos.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {
    @Autowired
    CoursRepository repos;

    public List<Cours> afficherCours(){
        return (List<Cours>) repos.findAll();
    }

    public Cours findById(int id){
        return repos.findById(id);
    }

    public List<Cours> findByUser(int userId){
        return repos.findByUserId(userId);
    }

}
