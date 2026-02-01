package test;


import notificacion.model.ChannelType;
import notificacion.model.Notification;
import notificacion.service.impl.SlackChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SlackChannelTest {

    private SlackChannel slackChannel;
    private Notification notification;

    @BeforeEach
    void setUp() {
        slackChannel = new SlackChannel();
        notification = Mockito.mock(Notification.class);
        Mockito.when(notification.getRecipient()).thenReturn("slackUser");
        Mockito.when(notification.getContent()).thenReturn("Mensaje Slack");
    }

    @Test
    void supports_shouldReturnFalseForEmail() {
        assertFalse(slackChannel.supports(ChannelType.EMAIL));
    }

    @Test
    void supports_shouldReturnFalseForSms() {
        assertFalse(slackChannel.supports(ChannelType.SMS));
    }

    @Test
    void supports_shouldReturnTrueForSlack() {
        assertTrue(slackChannel.supports(ChannelType.SLACK));
    }

    @Test
    void send_shouldPrintSlackMessage() {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        slackChannel.send(notification, ChannelType.SLACK);

        String output = outContent.toString();
        assertTrue(output.contains("Enviando mensaje por Slack a slackUser"));
        assertTrue(output.contains("Contenido: Mensaje Slack"));

        System.setOut(System.out);
    }
}
