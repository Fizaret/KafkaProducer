package az.ingress.kafkaproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {

    String username;

    String email;

    String age;

    String name;

    Double balance;

}
