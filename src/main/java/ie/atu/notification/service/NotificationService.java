package ie.atu.notification.service;

import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.client.UserClient;
import ie.atu.notification.dto.PaymentDto;
import ie.atu.notification.dto.UserDto;
import ie.atu.notification.model.Notification;
import ie.atu.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

     private final NotificationRepository repository;


  private final  UserClient userClient;
  private final PaymentClient paymentClient;
  private final EmailService emailService;


  public NotificationService(UserClient userClient, PaymentClient paymentClient, EmailService emailService, NotificationRepository repository) {
    this.userClient = userClient;
    this.paymentClient = paymentClient;
    this.emailService = emailService;
    this.repository = repository;
  }

    public Optional<Notification> getById(Long id) {
        return repository.findById(id);
    }
    public Notification createNotification(Notification notification) {

        return repository.save(notification);
    }
    public Optional<Notification> updateNotification(Long id, Notification updatedNotification) {
        return repository.findById(id).map(existing -> {
            existing.setToEmail(updatedNotification.getToEmail());
            existing.setSubject(updatedNotification.getSubject());
            existing.setMessage(updatedNotification.getMessage());
            return repository.save(existing);
        });
    }

    public void delete(Long id) {
        if(repository.findById(id).isPresent()) {

        repository.deleteById(id);}
        else {
            throw new IllegalArgumentException("Notification with id " + id + " not found");
        }
    }


  public void WelcomeEmail(String userId) {
      UserDto user = userClient.getByUserId(userId);

      String subject = "Welcome!";
      String body = "Hi " + user.getName() + ", welcome to our platform!";

      emailService.sendEmail(user.getEmail(), subject, body);
  }

  public void PaymentEmail(Long  paymentId) {
      PaymentDto payment = paymentClient.getPaymentById(paymentId);

      UserDto user = userClient.getByUserId(payment.getPaymentId().toString());

      String subject = "Payment!";
      String body = "Hi " + user.getName() + "Thanks for your payment" + "of"+ payment.getAmount();
      emailService.sendEmail(user.getEmail(), subject, body);
  }
    public void DefaultEmail(String to, String subject, String message) {
        emailService.sendEmail(to, subject, message);
    }








    /// Send welcome email


    /// Send Payment Conformation email

    /// ***Send item update email***
}
