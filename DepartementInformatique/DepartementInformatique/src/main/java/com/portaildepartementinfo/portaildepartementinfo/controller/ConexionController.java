package com.portaildepartementinfo.portaildepartementinfo.controller;

import com.portaildepartementinfo.portaildepartementinfo.entities.AccountType;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import com.portaildepartementinfo.portaildepartementinfo.service.UtilisateurService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ConexionController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("/deconnexion")
    public String pageDecon(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        session.invalidate(); // Invalide la session de l'utilisateur
        Cookie[] cookies = request.getCookies(); // Récupère tous les cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // Définit la durée de vie des cookies à 0 pour les supprimer
                response.addCookie(cookie); // Ajoute les cookies à la réponse
            }
        }
        return "deconnection";
    }


    @GetMapping("/espaceEP_Admin")
    public String pageEspaceEPAdmin(Model model, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        return "dashboardAdmin";
    }

    @GetMapping("/espaceEP_Professeur")
    public String pageEspaceEPProfesseur(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        return "dashboardProfesseur";
    }

    @GetMapping("/espaceEP_Etudiant")
    public String pageEspaceEPEtudiant(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        model.addAttribute("utilisateur", utilisateur);
        return "dashboardEleve";
    }



    @GetMapping("/inscription")
    public String afficherFormulaireUtilisateur(Model model) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        List<AccountType> listeRoles = utilisateurService.afficherRoles();
        model.addAttribute("listeRoles", listeRoles);
        model.addAttribute("inscriptionReussie", false); // Par défaut, l'inscription n'est pas réussie
        model.addAttribute("pageTitle", "Inscription");
        return "connect";
    }

    @PostMapping("/inscription/save")
    public String enregistrerUtilisateur(@ModelAttribute Utilisateur utilisateur, @RequestParam("utilisateur.accountType.id") int accountTypeId, Model model, RedirectAttributes redirectAttributes) {
        AccountType accountType = new AccountType();
        accountType.setId(accountTypeId);
        utilisateur.setAccountType(accountType);

        // Ajoutez ici la logique pour enregistrer l'utilisateur dans la base de données
        utilisateurService.enregistrerUtilisateur(utilisateur);


        redirectAttributes.addFlashAttribute("messageInscription", "Inscription réussie !");

 
        return "redirect:/";
    }

    @PostMapping("/connexion")
    public String submitLoginForm(@RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  @RequestParam(value = "sauvegarde", required = false) String rememberMe,
                                  HttpServletResponse response,
                                  HttpServletRequest request) {
        Utilisateur user = utilisateurService.validateUser(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("utilisateurConnecte", user);
            logger.info("LE utilisateur: {} ", user.getPrenom());
            if(rememberMe != null && rememberMe.equals("yes")){
                Cookie emailCookie = new Cookie("email",email);
                Cookie passwordCookie = new Cookie("password",password);
                emailCookie.setMaxAge(60 * 60 * 24 * 7);  // cookie expire apres 7 jour
                passwordCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(emailCookie);
                response.addCookie(passwordCookie);
            }
            // Vérifier le rôle de l'utilisateur et effectuer la redirection en conséquence
            if (user.getAccountType().getNom().equals("administrator")) {
                return "redirect:/espaceEP_Admin";
            }
            else if (user.getAccountType().getNom().equals("professeur")) {
                return "redirect:/espaceEP_Professeur";
            }
            else if (user.getAccountType().getNom().equals("etudiant")) {
                return "redirect:/espaceEP_Etudiant";
            }
            else
            {
                return "redirect:/";
            }

        }
        return "redirect:/inscription";
    }
}
