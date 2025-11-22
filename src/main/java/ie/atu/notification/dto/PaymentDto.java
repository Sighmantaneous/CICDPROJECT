package ie.atu.notification.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDto {

    private Long paymentId;
    private String amount;
    private String paymentMethod;
    private String currency;
}
