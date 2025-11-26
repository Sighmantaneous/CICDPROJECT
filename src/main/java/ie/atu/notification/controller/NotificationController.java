package ie.atu.notification.controller;

import ie.atu.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;







}
