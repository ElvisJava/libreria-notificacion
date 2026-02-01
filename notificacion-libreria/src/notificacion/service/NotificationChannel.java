package notificacion.service;

import notificacion.model.ChannelType;
import notificacion.model.Notification;

public interface NotificationChannel {

    void send(Notification notification, ChannelType type);
    boolean supports(ChannelType type);
}