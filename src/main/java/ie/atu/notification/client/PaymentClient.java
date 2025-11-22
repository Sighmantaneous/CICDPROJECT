package ie.atu.notification.client;


import ie.atu.notification.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-payment", url = "http://localhost:8081")
public interface PaymentClient {


}
