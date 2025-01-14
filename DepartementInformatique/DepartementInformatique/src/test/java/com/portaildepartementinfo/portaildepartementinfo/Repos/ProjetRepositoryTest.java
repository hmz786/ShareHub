package com.portaildepartementinfo.portaildepartementinfo.repos;

import com.portaildepartementinfo.portaildepartementinfo.entities.Projet;
import com.portaildepartementinfo.portaildepartementinfo.repos.ProjetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProjetRepositoryTest {

    @Autowired
    ProjetRepository projetRepository;



    @Test
    public void testCreateProjet(){
       // Projet projet= new Projet("portail","mpv.4","bonnes chose","2023");
       // Projet saveProduit = projetRepository.save(projet);
        //assertThat(saveProduit.getIdProjet()).isGreaterThan(0);
    }
}
