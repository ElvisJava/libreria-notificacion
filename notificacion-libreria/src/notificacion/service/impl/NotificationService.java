package notificacion.service.impl;

import notificacion.model.ChannelType;
import notificacion.model.Notification;
import notificacion.service.NotificationChannel;

import java.util.List;

public class NotificationService {
    private final List<NotificationChannel> channels;

    public NotificationService(List<NotificationChannel> channels) {
        this.channels = channels;
    }

    public void notify(Notification notification, ChannelType type) {
        channels.stream()
                .filter(channel -> channel.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Canal no soportado: " + type))
                .send(notification, type);
    }
}
