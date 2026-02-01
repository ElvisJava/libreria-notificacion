# 🔔 Universal Notification Library (Java)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

Una librería de notificaciones robusta y **agnóstica a frameworks**, diseñada bajo los principios **SOLID**. Esta arquitectura permite unificar el envío de mensajes a través de múltiples canales (Email, SMS, Slack, etc.) de manera transparente para la lógica de negocio.



## 🌟 Propósito
El objetivo de esta librería es eliminar el acoplamiento directo con proveedores externos (como SendGrid, Twilio o Mailgun). Si el proveedor cambia o se añade un nuevo canal, el código de la aplicación cliente permanece intacto.

## 🏗️ Arquitectura de la Solución

La librería utiliza una combinación de los patrones **Strategy** y **Dependency Inversion**:

1.  **Domain Objects**: `Notification` actúa como el DTO de transporte.
2.  **Abstracción**: `NotificationChannel` define el contrato que deben seguir todos los proveedores.
3.  **Orquestación**: `NotificationService` rutea la notificación al canal adecuado según el `ChannelType`.

## 🛠️ Instalación y Configuración

### 1. Implementar un Canal
Para añadir un nuevo proveedor, solo debes implementar la interfaz `NotificationChannel`.

```java
public class SendGridEmailChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        // Simulación de llamada HTTP a SendGrid
        System.out.println("Enviando Email vía SendGrid a: " + notification.getRecipient());
    }

    @Override
    public boolean supports(ChannelType type) {
        return type == ChannelType.EMAIL;
    }
}

List<NotificationChannel> activeChannels = List.of(
    new SendGridEmailChannel(),
    new TwilioSmsChannel()
);

NotificationService notificationService = new NotificationService(activeChannels);

Notification alert = new Notification(
    "+123456789", 
    "Alerta Médica", 
    "Su cita está programada para mañana."
);

// El servicio decide qué implementación usar basado en el ChannelType
notificationService.notify(alert, ChannelType.SMS);

# Ejemplo de ejecución de tests (si usas Maven)
mvn test
