package ie.atu.notification.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class UserDto {

    String Id;
    String Name;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    String Email;


}
