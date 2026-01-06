package ie.atu.notification.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.client.UserClient;
import ie.atu.notification.model.Notification;

import ie.atu.notification.service.EmailService;
import ie.atu.notification.service.NotificationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;


@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private UserClient userClient;
    @MockBean
    private PaymentClient paymentClient;
    @MockBean
    private EmailService emailService;

    @Test
    void createNotificationTest() throws Exception {

        Notification notification = new Notification();
        notification.setMessage("Hello World");
        notification.setSubject("Test");
        notification.setToEmail("test@atu.ie");

        when(notificationService.createNotification(any(Notification.class))).thenReturn(notification);

        ObjectMapper objectMapper = new ObjectMapper();
        String notificationJson = objectMapper.writeValueAsString(notification);
        mockMvc.perform(post("/api/notification").contentType(MediaType.APPLICATION_JSON).content(notificationJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Hello World"));

    }
    @Test
    void getByIdTest() throws Exception {

        Notification notification = new Notification();
        notification.setId(5L);
        notification.setMessage("Hello World");

        when(notificationService.getById(5L)).thenReturn(Optional.of(notification));

        mockMvc.perform(get("/api/notification/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(5L));

    }

    @Test
    void updateNotificationTest() throws Exception {
        Notification notification = new Notification();
        notification.setId(5L);
        notification.setMessage("Hello World");
        notification.setSubject("Test");
        notification.setToEmail("test@atu.ie");

        Notification updatedNotification = new Notification();
        updatedNotification.setId(5L);
        updatedNotification.setMessage("Updated Message");
        updatedNotification.setToEmail("test@atu.ie");
        updatedNotification.setSubject("Updated");

        when(notificationService.updateNotification(any(),any())).thenReturn(Optional.of(updatedNotification));

        mockMvc.perform(put("/api/notification/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notification)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("Updated Message"));

        verify(notificationService, times(1)).updateNotification(any(), any());
    }

    @Test
    void deleteNotificationTest() throws Exception {
        doNothing().when(notificationService).delete(5L);

        mockMvc.perform(delete("/api/notification/5"))
                .andExpect(status().isOk());

        verify(notificationService, times(1)).delete(5L);
    }


}
