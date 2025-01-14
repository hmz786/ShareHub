package com.portaildepartementinfo.portaildepartementinfo.service;

import com.portaildepartementinfo.portaildepartementinfo.entities.Notifications;
import com.portaildepartementinfo.portaildepartementinfo.entities.Utilisateur;
import com.portaildepartementinfo.portaildepartementinfo.repos.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationsService {
    @Autowired
    private NotificationsRepository repos;

    public List<Notifications> getNotificationsByUtilisateur(Utilisateur utilisateur) {
        return repos.findByUtilisateur(utilisateur);
    }

    public void addNotification(String message, Utilisateur utilisateur) {
        Notifications notification = new Notifications();
        notification.setMessage(message);
        notification.setUtilisateur(utilisateur);
        repos.save(notification);
    }
}
