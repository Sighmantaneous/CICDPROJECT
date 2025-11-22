package ie.atu.notification.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "service-user", url = "http://localhost:8080")
public interface UserClient {
}
