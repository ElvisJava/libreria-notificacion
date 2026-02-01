package test;

import notificacion.model.ChannelType;
import notificacion.model.Notification;
import notificacion.service.impl.SmsChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SmsChannelTest {

    private SmsChannel smsChannel;
    private Notification notification;

    @BeforeEach
    void setUp() {
        smsChannel = new SmsChannel();
        notification = Mockito.mock(Notification.class);
        Mockito.when(notification.getRecipient()).thenReturn("123456789");
        Mockito.when(notification.getContent()).thenReturn("Mensaje de prueba");
    }

    @Test
    void supports_shouldReturnFalseForEmail() {
        assertFalse(smsChannel.supports(ChannelType.EMAIL));
    }

    @Test
    void supports_shouldReturnFalseForSlack() {
        assertFalse(smsChannel.supports(ChannelType.SLACK));
    }

    @Test
    void supports_shouldReturnFalseForSms() {
        assertFalse(smsChannel.supports(ChannelType.SMS));
    }

    @Test
    void send_shouldPrintSmsMessage() {
        // Redirigir System.out para capturar la salida
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        smsChannel.send(notification, ChannelType.SMS);

        String output = outContent.toString();
        assertTrue(output.contains("Enviando SMS a 123456789"));
        assertTrue(output.contains("Contenido: Mensaje de prueba"));

        // Restaurar System.out
        System.setOut(System.out);
    }
}