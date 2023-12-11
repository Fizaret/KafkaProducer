package az.ingress.kafkaproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserResponseDto {
    private String name;

    private String surname;

    private Double balance;
}
