use departementdb;
INSERT INTO projets (nom_projet, description, video, annee_session, telecharger,url_projet_git,cours_id, user_id, evaluation_id)
VALUES ('Système de gestion des stocks', 'Un système informatisé pour gérer les stocks de manière efficace.', 'https://www.youtube.com/watch?v=video1', '2023-07-15', 'chemin/vers/fichier1.zip','https://git.dti.crosemont.quebec/hekram/pendu-hamza',4, 1, 9);

-- Insertion du Projet 2
INSERT INTO projets (nom_projet, description, video, annee_session, telecharger,url_projet_git,cours_id, user_id, evaluation_id)
VALUES ('Application de suivi des dépenses', 'Une application mobile pour suivre les dépenses personnelles.', 'https://www.youtube.com/watch?v=video2', '2023-08-20', 'chemin/vers/fichier2.zip','https://git.dti.crosemont.quebec/rmiah/projet-jeuxvideo2d-basketflame',1,2, 8);

-- Insertion du Projet 3
INSERT INTO projets (nom_projet, description, video, annee_session, telecharger,url_projet_git,cours_id, user_id, evaluation_id)
VALUES ('Plateforme apprentissage en ligne', 'Une plateforme pour offrir des cours en ligne interactifs.', 'https://www.youtube.com/watch?v=video3', '2023-09-10', 'chemin/vers/fichier3.zip','https://git.dti.crosemont.quebec/hekram/le_petit_blagueur',3, 3, 7);

-- Insertion du Projet 4
INSERT INTO projets (nom_projet, description, video, annee_session, telecharger,url_projet_git,cours_id, user_id, evaluation_id)
VALUES ('Application de gestion de tâches', 'Une application web pour gérer les tâches quotidiennes.', 'https://www.youtube.com/watch?v=video4', '2023-10-05', 'chemin/vers/fichier4.zip','https://git',2, 4, 6);


use departementdb;
INSERT INTO evaluation (commentaire, valeur_note, user_id) VALUES ('Travail bien fait, mais peut être amélioré.', 8.5, 1);

-- Insertion de l'Évaluation 2
INSERT INTO evaluation (commentaire, valeur_note, user_id) VALUES ('Très bon travail, excellentes performances.', 90, 3);

-- Insertion de l'Évaluation 3
INSERT INTO evaluation (commentaire, valeur_note, user_id) VALUES ('Des résultats satisfaisants, mais des aspects peuvent être affinés.', 75, 3);

-- Insertion de l'Évaluation 4
INSERT INTO evaluation (commentaire, valeur_note, user_id) VALUES ('Travail moyen, des améliorations nécessaires.', 60, 3);


use departementdb;
INSERT INTO cours (nom, numero, description, salle, horaire, credits, user_id)
VALUES ('Langue et culture françaises', 100, 'Cours sur la langue et la culture françaises', 'Salle B202', '13:00-14:30', 3, 3);

-- Insertion d'un autre Cours
INSERT INTO cours (nom, numero, description, salle, horaire, credits, user_id)
VALUES ('Introduction à la programmation', 110, 'Cours introduction à la programmation informatique', 'Salle C103', '10:00-11:30', 4, 3);

-- Insertion d'un autre Cours
INSERT INTO cours (nom, numero, description, salle, horaire, credits, user_id)
VALUES ('Économie mondiale', 201, 'Cours sur économie mondiale et les marchés financiers', 'Salle A205', '15:00-16:30', 3, 3);

INSERT INTO utilisateurs (nom, prenom, email, motDePasse, tuteur, accounttype_id)
VALUES ('Doe', 'John', 'john.doe@example.com', 'motdepasse123', false, 1),
       ('Smith', 'Jane', 'jane.smith@example.com', 'password456', true, 2),
       ('Johnson', 'Michael', 'michael.johnson@example.com', 'securepass789', false, 1),
       ('Williams', 'Emily', 'emily.williams@example.com', 'strongpassword321', true, 2);

INSERT INTO tbl_accountType (nom) VALUES (
                                             1,
                                             'administrator'
                                         );
INSERT INTO tbl_accountType (nom) VALUES (
                                             2,
                                             'etudiant'
                                         );
INSERT INTO tbl_accountType (nom) VALUES (
                                             2,
                                             'professeur'
                                         );
INSERT INTO tbl_accountType (nom) VALUES (
                                             3,
                                             'Tuteur'
                                         );
INSERT INTO tbl_accountType (nom) VALUES (
                                             4,
                                             'ancienEtudiant'
                                         );
INSERT INTO tbl_accountType (nom) VALUES (
                                             5,
                                             'Moniteur/MembreJury'
                                         );


