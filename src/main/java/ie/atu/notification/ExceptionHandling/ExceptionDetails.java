package ie.atu.notification.ExceptionHandling;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {

    String FieldName;
    String FieldMessage;

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getFieldMessage() {
        return FieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        FieldMessage = fieldMessage;
    }
}