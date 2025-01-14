ShareHub

Description

ShareHub est une plateforme collaborative développée pour le département informatique. Elle permet aux enseignants et étudiants de partager des ressources, des projets, et des notes de cours. La plateforme propose également un service de tutorat pour aider les étudiants en difficulté et un espace dédié aux anciens étudiants.

Fonctionnalités principales

Visiteurs

Consulter la description de l’application.

Visualiser les projets des étudiants et leurs vidéos de présentation.

Étudiants

Créer un compte et gérer leurs projets (déposer, télécharger, consulter).

Accéder aux notes de cours et effectuer des recherches.

S’inscrire au service de tutorat en tant qu’étudiant en difficulté ou tuteur.

Rejoindre le réseau des anciens étudiants.

Professeurs

Partager du matériel pédagogique et consulter celui de leurs collègues.

Évaluer les projets étudiants et faire des recommandations.

Fournir des liens utiles et recommander des lectures.

Administrateurs

Gérer les utilisateurs, projets, notes de cours et autres ressources de la plateforme.

Technologies utilisées

Backend : Spring Boot

Frontend : HTML, CSS, Bootstrap, JavaScript

Base de données : MySQLwokbench

Installation et exécution

Prérequis

Java 17 ou version supérieure

Maven

Un serveur de base de données MySQL

Clonez le projet

git clone https://github.com/hmz786/ShareHub.git
cd ShareHub

Configuration de la base de données

Modifiez le fichier application.properties dans src/main/resources pour ajouter vos informations de connexion à la base de données.

Exemple :

spring.datasource.url=jdbc:mysql://localhost:3306/sharehub
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe

Compilez et exécutez l’application

mvn spring-boot:run

Accédez à l’application
Ouvrez votre navigateur et accédez à http://localhost:8080.


Auteur

Projet développé par Hamza Ekram et Aicha.
