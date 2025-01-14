package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.controller.AppController;
import com.portaildepartementinfo.portaildepartementinfo.entities.Message;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import com.portaildepartementinfo.portaildepartementinfo.repos.MessageRepository;
import com.portaildepartementinfo.portaildepartementinfo.repos.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    public List<Message> getMessagesWithUser() {
        List<Message> messages = messageRepository.findAll();

        for (Message message : messages) {
            if (message.getUtilisateur() == null) {
                Utilisateur utilisateur = utilisateurRepository.findById(3).orElse(null);
                message.setUtilisateur(utilisateur);
                messageRepository.save(message);
                logger.info("Message ID {} associé à l'utilisateur {}", message.getId(), utilisateur.getNom());
            } else {
                Utilisateur utilisateur = utilisateurRepository.findById(message.getUtilisateur().getId()).orElse(null);
                message.setUtilisateur(utilisateur);
                messageRepository.save(message);
            }
        }

        return messages;
    }

    public void envoyerMessage(Utilisateur utilisateur, String contenu) {

        Message message = new Message(contenu);
        message.setUtilisateur(utilisateur);

        messageRepository.save(message);
    }



}