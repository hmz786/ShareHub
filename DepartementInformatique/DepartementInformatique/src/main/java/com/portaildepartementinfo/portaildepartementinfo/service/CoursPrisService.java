package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.Cours;
import com.portaildepartementinfo.portaildepartementinfo.entities.CoursPris;
import com.portaildepartementinfo.portaildepartementinfo.repos.CoursPrisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursPrisService {
    @Autowired
    CoursPrisRepository repos;

    public List<CoursPris> getCoursByUtilisateurId(int utilisateurId) {
        return repos.findByUtilisateurId(utilisateurId);
    }

}
