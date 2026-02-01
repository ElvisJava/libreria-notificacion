package notificacion.service.impl;

import notificacion.model.ChannelType;
import notificacion.model.Notification;
import notificacion.service.NotificationChannel;

// Implementación para Email (Podría ser SendGrid o Mailgun internamente)
public class EmailChannel implements NotificationChannel {

    @Override
    public void send(Notification notification, ChannelType type) {
        // Lógica para enviar email
        System.out.println("Enviando Email a " + notification.getRecipient());
        System.out.println("Asunto: " + notification.getSubject());
        System.out.println("Contenido: " + notification.getContent());
    }

    @Override
    public boolean supports(ChannelType type) {
        return type == ChannelType.EMAIL;
    }

}