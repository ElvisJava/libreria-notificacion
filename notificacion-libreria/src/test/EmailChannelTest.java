package test;

import notificacion.model.ChannelType;
import notificacion.model.Notification;
import notificacion.service.impl.EmailChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class EmailChannelTest {

    private EmailChannel emailChannel;
    private Notification notification;

    @BeforeEach
    void setUp() {
        emailChannel = new EmailChannel();
        notification = Mockito.mock(Notification.class);
        Mockito.when(notification.getRecipient()).thenReturn("user@example.com");
        Mockito.when(notification.getSubject()).thenReturn("Asunto de Prueba");
        Mockito.when(notification.getContent()).thenReturn("Contenido de prueba");
    }

    @Test
    void supports_shouldReturnTrueForEmail() {
        assertTrue(emailChannel.supports(ChannelType.EMAIL));
    }

    @Test
    void supports_shouldReturnFalseForSms() {
        assertFalse(emailChannel.supports(ChannelType.SMS));
    }

    @Test
    void supports_shouldReturnFalseForSlack() {
        assertFalse(emailChannel.supports(ChannelType.SLACK));
    }

    @Test
    void send_shouldPrintEmailMessage() {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        emailChannel.send(notification, ChannelType.EMAIL);

        String output = outContent.toString();
        assertTrue(output.contains("Enviando correo a user@example.com"));
        assertTrue(output.contains("Asunto: Asunto de Prueba"));
        assertTrue(output.contains("Contenido: Contenido de prueba"));

        System.setOut(System.out);
    }
}
