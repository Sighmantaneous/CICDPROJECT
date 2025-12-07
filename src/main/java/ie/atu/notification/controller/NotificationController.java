package ie.atu.notification.controller;

import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.dto.PaymentDto;
import ie.atu.notification.model.Notification;
import ie.atu.notification.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {


    NotificationService notificationService;
    PaymentClient paymentClient;

     public NotificationController(NotificationService notificationService, PaymentClient paymentClient) {
        this.notificationService = notificationService;
        this.paymentClient = paymentClient;
    }

 @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable Long id) {
        return notificationService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
 }
    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        Notification created = notificationService.createNotification(notification);
        return ResponseEntity
                .created(URI.create("/api/notifications/" + created.getId()))
                .body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Notification> update(@PathVariable Long id, @RequestBody Notification notification) {
        return notificationService.updateNotification(id, notification)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notificationService.delete(id);

    }

    @GetMapping("/api/notification/{id}")
    public ResponseEntity<PaymentDto> getAllPayments(@PathVariable Long id) {

         ResponseEntity<PaymentDto> response = paymentClient.fromPaymentId(id);

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());




    }












}
