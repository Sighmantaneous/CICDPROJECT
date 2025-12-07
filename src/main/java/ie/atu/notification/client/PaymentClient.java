package ie.atu.notification.client;


import ie.atu.notification.dto.PaymentDto;
import ie.atu.notification.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "paymentClient", url = "${payment.service.base-url}")

public interface PaymentClient {


    @GetMapping("/api/payment/{paymentId}")
    ResponseEntity<PaymentDto> fromPaymentId(@PathVariable Long paymentId);


    @GetMapping("/payments/{id}")
    PaymentDto getPaymentById(@PathVariable("id") Long paymentId);

    @GetMapping("/api/payments")
    List<PaymentDto> getPayments();
}




