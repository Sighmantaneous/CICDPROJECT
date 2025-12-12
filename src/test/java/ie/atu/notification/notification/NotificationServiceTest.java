package ie.atu.notification.notification;

import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.client.UserClient;
import ie.atu.notification.model.Notification;
import ie.atu.notification.repository.NotificationRepository;
import ie.atu.notification.service.EmailService;
import ie.atu.notification.service.NotificationService;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository repository;

    @Mock
    private EmailService emailService;
    @Mock
    private UserClient userClient;
    @Mock
    private PaymentClient paymentClient;
    @InjectMocks
    private NotificationService service;



    @Test
    void createNotificationAndGetById() {
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




    }

    @Test
    void updateNotification() {
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


    }

    }