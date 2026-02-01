package notificacion.service.impl;

import notificacion.model.ChannelType;
import notificacion.model.Notification;
import notificacion.service.NotificationChannel;

public class SmsChannel implements NotificationChannel {
    @Override
    public void send(Notification notification, ChannelType type) {
        // Lógica para enviar SMS
        System.out.println("Enviando SMS a " + notification.getRecipient());
        System.out.println("Contenido: " + notification.getContent());
    }

    @Override
    public boolean supports(ChannelType type) {
        return type == ChannelType.EMAIL;
    }
}
