package ie.atu.notification.client;


import ie.atu.notification.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-user", url = "http://localhost:8080")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDto getByUserId(@PathVariable("id") String id);
}
