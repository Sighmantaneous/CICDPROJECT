package ie.atu.notification.service;

import ie.atu.notification.client.PaymentClient;
import ie.atu.notification.client.UserClient;
import ie.atu.notification.dto.PaymentDto;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

public class PollingPaymentService {

    private PaymentClient paymentClient;
    private NotificationService notificationService;
    private List<Long> newPayments = new ArrayList<>();

    public PollingPaymentService(PaymentClient paymentClient, NotificationService notificationService) {
        this.paymentClient = paymentClient;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 60000)
    public void checkNewPayments() {
        List<PaymentDto> payments = paymentClient.getPayments();

        for(PaymentDto payment : payments) {
            if(!newPayments.contains(payment.getPaymentId())){
                notificationService.PaymentEmail(payment.getPaymentId());
                newPayments.add(payment.getPaymentId());
            }
        }
    }
}
