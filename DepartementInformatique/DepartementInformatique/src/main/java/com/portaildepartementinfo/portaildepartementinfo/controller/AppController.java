package com.portaildepartementinfo.portaildepartementinfo.controller;

import com.portaildepartementinfo.portaildepartementinfo.entities.Message;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import com.portaildepartementinfo.portaildepartementinfo.service.ChatService;
import com.portaildepartementinfo.portaildepartementinfo.service.ProjetService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    @Autowired
    ProjetService projetService;
    @Autowired
    ChatService chatService;

    @GetMapping("")
    public String pageAccueil (Model model, HttpSession session, RedirectAttributes redirectAttributes){

        String messageInscription = (String) redirectAttributes.getAttribute("messageInscription");
        if(messageInscription !=null){
            model.addAttribute("messageInscription",messageInscription);
        }

        Utilisateur utilisateur=(Utilisateur) session.getAttribute("user");
        if(utilisateur != null){
            model.addAttribute("nomUtilisateur",utilisateur.getNom());
            model.addAttribute("prenomUtilisateur",utilisateur.getPrenom());

        }else {
            model.addAttribute("nomUtilisateur","Visiteur");
            model.addAttribute("prenomUtilisateur","Visiteur");
        }
        return "index";
    }

    @GetMapping("/Propos")
    public String pagePropos(){
        return "propos";
    }



    @GetMapping("/chat")
    public String pageChat(Model model, HttpSession session, Message message) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        logger.info("LUtilisateur connecter: {} ", utilisateur);


        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("messagess", new Message());

        List<Message> messages = chatService.getMessagesWithUser();
        model.addAttribute("messages", messages);

        return "chat";
    }

    @PostMapping("/chat/envoyer-message")
    public String envoyerMessage(@RequestParam("message") String messageContent, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        logger.info("LE utilisateur: {} ", utilisateur);
        logger.info("LE CONTENUE: {} ", messageContent);
            chatService.envoyerMessage(utilisateur, messageContent);
            return "redirect:/chat";
        }

}
