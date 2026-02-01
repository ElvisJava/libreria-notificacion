import notificacion.model.ChannelType;
import notificacion.model.Notification;
import notificacion.service.NotificationChannel;
import notificacion.service.impl.EmailChannel;
import notificacion.service.impl.NotificationService;
import notificacion.service.impl.SlackChannel;
import notificacion.service.impl.SmsChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        // Configuración de la librería (Inyección de dependencias manual)
        List<NotificationChannel> availableChannels = new ArrayList<>();
        availableChannels.add(new EmailChannel());
        availableChannels.add(new SmsChannel());
        availableChannels.add(new SlackChannel());

        NotificationService service = new NotificationService(availableChannels);

        // Uso transparente
        Notification alert = new Notification("user@example.com", "Alerta de Seguridad", "Se ha iniciado sesión.");

        // El cliente solo decide el tipo, no la implementación
        service.notify(alert, ChannelType.EMAIL);

    }
}