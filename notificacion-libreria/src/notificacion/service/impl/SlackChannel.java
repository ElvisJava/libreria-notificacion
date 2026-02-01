package notificacion.service.impl;

import notificacion.model.Notification;
import notificacion.service.NotificationChannel;

public class SlackChannel implements NotificationChannel {
    @Override
    public void send(Notification notification, notificacion.model.ChannelType type) {
        // Lógica para enviar notificación por Slack
        System.out.println("Enviando notificación por Slack a " + notification.getRecipient());
        System.out.println("Asunto: " + notification.getSubject());
        System.out.println("Contenido: " + notification.getContent());
    }

    @Override
    public boolean supports(notificacion.model.ChannelType type) {
        return type == notificacion.model.ChannelType.SLACK;
    }
}
