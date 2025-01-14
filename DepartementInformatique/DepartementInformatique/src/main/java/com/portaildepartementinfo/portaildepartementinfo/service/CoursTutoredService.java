package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.CoursTutored;
import com.portaildepartementinfo.portaildepartementinfo.repos.CoursTutoredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursTutoredService {
    @Autowired
    private CoursTutoredRepository repos;

    public CoursTutored save(CoursTutored coursTutored) {
        return repos.save(coursTutored);
    }
}
