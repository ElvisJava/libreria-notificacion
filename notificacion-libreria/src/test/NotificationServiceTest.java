// src/test/NotificationServiceTest.java
package test;

import notificacion.model.ChannelType;
import notificacion.model.Notification;
import notificacion.service.NotificationChannel;
import notificacion.service.impl.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private NotificationChannel emailChannel;
    private NotificationChannel smsChannel;
    private Notification notification;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        emailChannel = Mockito.mock(NotificationChannel.class);
        smsChannel = Mockito.mock(NotificationChannel.class);
        notification = Mockito.mock(Notification.class);

        // emailChannel soporta EMAIL, smsChannel soporta SMS
        when(emailChannel.supports(ChannelType.EMAIL)).thenReturn(true);
        when(emailChannel.supports(ChannelType.SMS)).thenReturn(false);
        when(smsChannel.supports(ChannelType.EMAIL)).thenReturn(false);
        when(smsChannel.supports(ChannelType.SMS)).thenReturn(true);

        notificationService = new NotificationService(Arrays.asList(emailChannel, smsChannel));
    }

    @Test
    void notify_shouldCallSendOnSupportedChannel() {
        notificationService.notify(notification, ChannelType.EMAIL);
        verify(emailChannel, times(1)).send(notification, ChannelType.EMAIL);
        verify(smsChannel, never()).send(any(), any());
    }

    @Test
    void notify_shouldThrowExceptionIfNoChannelSupportsType() {
        when(emailChannel.supports(ChannelType.SLACK)).thenReturn(false);
        when(smsChannel.supports(ChannelType.SLACK)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            notificationService.notify(notification, ChannelType.SLACK);
        });
        assertTrue(exception.getMessage().contains("Canal no soportado"));
    }
}