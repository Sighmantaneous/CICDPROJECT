package ie.atu.notification.notification;

import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.client.UserClient;
import ie.atu.notification.model.Notification;
import ie.atu.notification.repository.NotificationRepository;
import ie.atu.notification.service.EmailService;
import ie.atu.notification.service.NotificationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository repository;

    @InjectMocks
    private NotificationService service;



    @Test
    void createNotificationAndGetByIdTest() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Hello World");
        notification.setSubject("Test Subject");
        notification.setToEmail("You@atu.ie");


        when(repository.save(notification)).thenReturn(notification);
        when(repository.findById(1L)).thenReturn(Optional.of(notification));

        service.createNotification(notification);


        Optional<Notification> created = service.getById(1L);

        assertTrue(created.isPresent());
        assertEquals("Hello World", created.get().getMessage());
        assertEquals("Test Subject", created.get().getSubject());
        assertEquals("You@atu.ie", created.get().getToEmail());

        verify(repository, times(1)).save(notification);
        verify(repository, times(1)).findById(1L);




    }

    @Test
    void updateNotificationTest() {
        Notification old = new Notification();

        old.setId(7L);
        old.setMessage("Hello World");
        old.setSubject("Test Subject");
        old.setToEmail("Past@atu.ie");

        Notification updated = new Notification();

        updated.setMessage("Updated Message");
        updated.setSubject("Updated Subject");
        updated.setToEmail("New@atu.ie");


        when(service.getById(7L)).thenReturn(Optional.of(old));
        when(service.createNotification(old)).thenReturn(old);

        Optional<Notification> result = service.updateNotification(7L, updated);


        assertTrue(result.isPresent());

        assertEquals("Updated Message", result.get().getMessage());
        assertEquals("Updated Subject", result.get().getSubject());
        assertEquals("New@atu.ie", result.get().getToEmail());

        verify(repository, times(1)).findById(7L);
        verify(repository, times(1)).save(old);


    }
    @Test
    void deleteTest(){
        Notification notification = new Notification();
        notification.setId(5L);
        notification.setMessage("Hello World");
        notification.setSubject("Test Subject");
        notification.setToEmail("Past@atu.ie");

        when(repository.findById(5L)).thenReturn(Optional.of(notification));
        doNothing().when(repository).deleteById(5L);

        service.delete(5L);

        verify(repository, times(1)).findById(5L);
        verify(repository, times(1)).deleteById(5L);

    }

    }