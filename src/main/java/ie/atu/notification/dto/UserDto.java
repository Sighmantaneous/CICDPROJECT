package ie.atu.notification.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class UserDto {

    String id;
    String name;
    String email;


}
