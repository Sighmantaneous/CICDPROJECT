package ie.atu.notification.service;

import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.client.UserClient;
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


      emailService.sendEmail(
              user.getEmail(),
              "Welcome!",
              "Hi " + user.getName() + ", welcome to our platform!"
      );



  }





    /// Send welcome email


    /// Send Payment Conformation email

    /// ***Send item update email***
}
