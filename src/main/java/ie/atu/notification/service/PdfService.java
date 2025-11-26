package ie.atu.notification.service;


import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

    @Autowired
    private final NotificationService notificationService;
    @Autowired
    private final PaymentClient paymentClient;


    public PdfService(NotificationService notificationService, PaymentClient paymentClient) {
        this.notificationService = notificationService;
        this.paymentClient = paymentClient;
    }

    public void generatePdf(Notification notification) {}


}
