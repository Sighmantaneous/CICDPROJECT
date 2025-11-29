package ie.atu.notification.service;

import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.client.UserClient;
import ie.atu.notification.dto.PaymentDto;
import ie.atu.notification.dto.UserDto;
import ie.atu.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

     private final NotificationRepository repository;


  private final  UserClient userClient;
  private final PaymentClient paymentClient;
  private final EmailService emailService;
  private final PdfService pdfService;


  public NotificationService(UserClient userClient, PaymentClient paymentClient, EmailService emailService,  PdfService pdfService, NotificationRepository repository) {
    this.userClient = userClient;
    this.paymentClient = paymentClient;
    this.emailService = emailService;
    this.pdfService = pdfService;
    this.repository = repository;
  }











  public void WelcomeEmail(String userId) {
      UserDto user = userClient.getByUserId(userId);

      String subject = "Welcome!";
      String body = "Hi " + user.getName() + ", welcome to our platform!";

      emailService.sendEmail(user.getEmail(), subject, body);
  }

  public void PaymentEmail(Long  paymentId) {
      PaymentDto payment = paymentClient.getPaymentById(paymentId);

      UserDto user = userClient.getByUserId(payment.getUserId().toString());

      String subject = "Payment!";
      String body = "Hi " + user.getName() + "Thanks for your payment" + "of"+ payment.getAmount();
      emailService.sendEmail(user.getEmail(), subject, body);
  }
    public void DefaultEmail(String to, String subject, String message) {
        emailService.sendEmail(to, subject, message);
    }


    public void PdfGenerator(Long paymentId) {

      UserDto user = userClient.getByUserId((paymentId.toString()));

      PaymentDto payment = paymentClient.getPaymentById(paymentId);

        System.out.println("Payment ID: " + paymentId +
                "Name" +user.getName() +
                "Payment amount " + payment.getAmount() +
                "Payment Method" + payment.getPaymentMethod() +
                "Currency" + payment.getCurrency());

    }





    /// Send welcome email


    /// Send Payment Conformation email

    /// ***Send item update email***
}
