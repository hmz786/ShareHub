

package com.portaildepartementinfo.portaildepartementinfo.repos;
import com.portaildepartementinfo.portaildepartementinfo.entities.Cours;
import com.portaildepartementinfo.portaildepartementinfo.entities.LiensUtiles;
import com.portaildepartementinfo.portaildepartementinfo.entities.NotesDeCours;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest  // permet de configurer l'environnement de test en chargement les éléments necessaires
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// configuration d'une base des données de test
//  AutoConfigureTestDatabase.Replace.NONE indique à ne pas remplacer la base des données par défaut
@Rollback(false)  // empecher la suppression des données créée

class LiensUtilesRepositoryTest {
    @Autowired
    private LiensUtilesRepository liensUtilesRepository;
    private TestEntityManager entityManager;
    @Test
    public void testSaveLiensUtiles() {

      LiensUtiles lien = new LiensUtiles ("w3school.com");
        LiensUtiles saveLien = liensUtilesRepository.save(lien);
        assertThat(saveLien.getId()).isGreaterThan(0);
    }

    }