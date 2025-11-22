package ie.atu.notification.service;


import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String body) {

        System.out.println("To: " + to +
        ",Subject: " +  subject +
        "Body: " +  body );
    }
}
