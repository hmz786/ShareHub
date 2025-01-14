package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.Evaluation;
import com.portaildepartementinfo.portaildepartementinfo.entities.Projet;
import com.portaildepartementinfo.portaildepartementinfo.repos.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    EvaluationRepository repos;

    public void save(Evaluation e) {
        repos.save(e);
    }

    public List<Evaluation> findByProjet(Projet projet) {
        return repos.findByProjet(projet);
    }
}
