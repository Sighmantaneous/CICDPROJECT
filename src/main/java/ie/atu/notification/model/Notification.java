package ie.atu.notification.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Table(name = "Notifications")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Recipient email is required")
    private String toEmail;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Message is required")
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Recipient email is required") String getToEmail() {
        return toEmail;
    }

    public void setToEmail(@NotBlank(message = "Recipient email is required") String toEmail) {
        this.toEmail = toEmail;
    }

    public @NotBlank(message = "Subject is required") String getSubject() {
        return subject;
    }

    public void setSubject(@NotBlank(message = "Subject is required") String subject) {
        this.subject = subject;
    }

    public @NotBlank(message = "Message is required") String getMessage() {
        return message;
    }

    public void setMessage(@NotBlank(message = "Message is required") String message) {
        this.message = message;
    }
}
