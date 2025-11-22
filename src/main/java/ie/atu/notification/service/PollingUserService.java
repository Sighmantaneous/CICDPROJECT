package ie.atu.notification.service;

import ie.atu.notification.client.UserClient;
import ie.atu.notification.dto.UserDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollingUserService {

    private final UserClient userClient;
    private final NotificationService notificationService;
    private final List<String> userList = new ArrayList<>();

    public PollingUserService(UserClient userClient, NotificationService notificationService) {
        this.notificationService = notificationService;
        this.userClient = userClient;
    }

    @Scheduled(fixedRate = 60000)
    public void CheckCreatedUser(){
        List<UserDto> users = userClient.getAll();

        for (UserDto user : users) {
            if(!userList.contains(user.getId())){
                notificationService.WelcomeEmail(user.getId());
                userList.add(user.getId());
            }
        }
    }
}
