package ie.atu.notification.service;

import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.client.UserClient;
import ie.atu.notification.dto.PaymentDto;
import ie.atu.notification.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final  UserClient userClient;
  private final PaymentClient paymentClient;
  private final EmailService emailService;


  public NotificationService(UserClient userClient, PaymentClient paymentClient, EmailService emailService) {
    this.userClient = userClient;
    this.paymentClient = paymentClient;
    this.emailService = emailService;
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
